package org.example.reader.excel;

public class ExcelDataField extends DataField
{
  private final String column;
  private final String title;

  public ExcelDataField(final String column
      , final String name
      , final String title)
  {
    this(column, name, title, String.class);
  }

  public ExcelDataField(final String column
      , final String name
      , final String title
      , final Class<?> type)
  {
    super(name, type);

    if (column == null)
    {
      throw new NullPointerException("Argument [column] must not be null.");
    }
    else if (!column.matches("[A-Za-z]{1,2}"))
    {
      throw new IllegalArgumentException("Argument [column] must be between A and XFD.");
    }

    if (title == null)
    {
      throw new NullPointerException("Argument [title] must not be null.");
    }

    this.column = column.trim();
    this.title = title.trim();
  }

  public final String getColumn()
  {
    return column;
  }

  public final String getTitle()
  {
    return title;
  }
}
