/*
 * See the file license.txt in the main project folder
 * for license and other legal information.
 */

package org.example.reader;

import org.example.reader.core.StringFieldReader;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;

public abstract class DataFieldReader<FieldType extends Serializable> implements IDataFieldReader<FieldType>
{
  private static Map<Class<?>, IDataFieldReader<?>> READERS;

  public static IDataFieldReader<?> getReader(final Class<?> type)
  {
    return getReaders().get(type);
  }

  @Override
  public FieldType get(final Object value)
  {
    return this.get(value, null);
  }

  @Override
  public final FieldType get(final Object value, final FieldType defaultValue)
  {
    return value != null ? this.convert(value.toString().trim()) : defaultValue;
  }

  protected abstract FieldType convert(final String value);

  private static Map<Class<?>, IDataFieldReader<?>> getReaders()
  {
    if (READERS == null)
    {
      synchronized (DataFieldReader.class)
      {
        if (READERS == null)
        {
          READERS = Collections.singletonMap(String.class, StringFieldReader.INSTANCE);
        }
      }
    }

    return READERS;
  }
}
