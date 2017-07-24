package org.example.reader.excel;

public abstract class DataField
{
  private final String   name;
  private final Class<?> type;

  public DataField(final String name)
  {
    this(name, String.class);
  }

  DataField(final String name, final Class<?> type)
  {
    if (name == null)
    {
      throw new NullPointerException("Argument [name] must not be null.");
    }

    if (type == null)
    {
      throw new NullPointerException("Argument [type] must not be null.");
    }

    this.name = name.trim();
    this.type = type;
  }

  public final String getName()
  {
    return this.name;
  }

  public final Class<?> getType()
  {
    return this.type;
  }
}
