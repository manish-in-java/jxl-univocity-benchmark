/*
 * See the file license.txt in the main project folder
 * for license and other legal information.
 */

package org.example.reader.delimited;

import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.example.reader.DataReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public abstract class DelimitedDataReader<RecordType> extends DataReader<RecordType>
{
  private final Class<RecordType> clazz;

  protected DelimitedDataReader(final Class<RecordType> clazz)
  {
    super();

    this.clazz = clazz;
  }

  public List<RecordType> getRecords(final InputStream stream)
  {
    if (stream == null)
    {
      throw new NullPointerException("Argument [stream] must not be null.");
    }
    else
    {
      try (final InputStreamReader reader = new InputStreamReader(stream))
      {
        final BeanListProcessor<RecordType> processor = new BeanListProcessor<>(clazz);

        final CsvParserSettings csvParserSettings = new CsvParserSettings();
        csvParserSettings.setHeaderExtractionEnabled(true);
        csvParserSettings.setLineSeparatorDetectionEnabled(true);
        csvParserSettings.setProcessor(processor);

        new CsvParser(csvParserSettings).parse(reader);

        return processor.getBeans();
      }
      catch (final IOException e)
      {
        throw new RuntimeException(e);
      }
      finally
      {
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

  @Override
  protected RecordType getRecord()
  {
    return null;
  }
}
