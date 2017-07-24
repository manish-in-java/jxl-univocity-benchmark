package org.example.reader.excel;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.example.reader.DataFieldReader;
import org.example.reader.DataReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ExcelDataReader<RecordType> extends DataReader<RecordType>
{
  private static final int HEADER_ROW = 1;

  private final ExcelDataField[] fields;
  private final int              firstDataRow;
  private final int              headerRow;
  private final int              lastDataRow;
  private final String           sheetName;

  protected ExcelDataReader(final String sheetName
      , final int dataRows
      , final ExcelDataField... fields)
  {
    super();

    if (fields == null)
    {
      throw new NullPointerException("Argument [fields] must not be null.");
    }
    else if (fields.length == 0)
    {
      throw new IllegalArgumentException("At least one field needs to be specified for reading data from an external source.");
    }

    if (dataRows < 1)
    {
      throw new IllegalArgumentException("Argument [dataRows] must be greater than 0.");
    }

    if (sheetName == null)
    {
      throw new NullPointerException("Argument [sheetName] must not be null.");
    }

    this.fields = fields;
    this.firstDataRow = HEADER_ROW + 1;
    this.headerRow = HEADER_ROW;
    this.lastDataRow = HEADER_ROW + dataRows;
    this.sheetName = sheetName.trim();
  }

  /**
   * {@inheritDoc}
   */
  public List<RecordType> getRecords(final InputStream stream)
  {
    if (stream == null)
    {
      throw new NullPointerException("Argument [stream] must not be null.");
    }
    else
    {
      Workbook workbook = null;
      try
      {
        workbook = Workbook.getWorkbook(stream);

        disableWarnings();

        return read(workbook);
      }
      catch (final BiffException e)
      {
        throw new RuntimeException(e);
      }
      catch (final IOException e)
      {
        throw new RuntimeException(e);
      }
      finally
      {
        if (workbook != null)
        {
          workbook.close();
        }

        try
        {
          stream.close();
        }
        catch (final IOException e)
        {
          throw new RuntimeException(e);
        }
      }
    }
  }

  protected final String getString(final Sheet worksheet, final String column, final int row)
  {
    return this.getCellContent(worksheet, column, row);
  }

  private void disableWarnings()
  {
    jxl.common.Logger.getLogger(jxl.Workbook.class).setSuppressWarnings(true);
  }

  private String getCellContent(final Sheet worksheet, final String column, final int row)
  {
    return worksheet.getCell(String.format("%s%d", column, row)).getContents();
  }

  private boolean isWorksheetStructureValid(final Sheet worksheet)
  {
    boolean result = true;

    for (final ExcelDataField field : this.fields)
    {
      if (!field.getTitle().equals(this.getCellContent(worksheet, field.getColumn(), this.headerRow)))
      {
        result = false;
        break;
      }
    }

    return result;
  }

  private List<RecordType> read(final Workbook workbook)
  {
    List<RecordType> records = null;

    final Sheet worksheet = workbook.getSheet(this.sheetName);

    if (worksheet == null)
    {
      throw new NullPointerException("Argument [worksheet] must not be null.");
    }
    else if (!this.isWorksheetStructureValid(worksheet))
    {
      throw new IllegalArgumentException("Argument [worksheet] does not have the expected structure.");
    }
    else
    {
      records = new ArrayList<>();

      final Map<String, Object> properties = new HashMap<>();
      final int rows = Math.min(worksheet.getRows(), lastDataRow);
      for (int row = firstDataRow; row <= rows; ++row)
      {
        // Read data fields from the current row.
        for (final ExcelDataField field : this.fields)
        {
          final String value = this.getString(worksheet, field.getColumn(), row);

          properties.put(field.getName(), DataFieldReader.getReader(field.getType()).get(value));
        }

        if (!properties.isEmpty())
        {
          records.add(this.getRecord(properties));

          properties.clear();
        }
      }
    }

    return records;
  }
}
