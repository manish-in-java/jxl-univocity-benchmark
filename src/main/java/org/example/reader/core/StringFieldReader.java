/*
 * See the file license.txt in the main project folder
 * for license and other legal information.
 */

package org.example.reader.core;

import org.example.reader.DataFieldReader;

public final class StringFieldReader extends DataFieldReader<String>
{
  public static final StringFieldReader INSTANCE = new StringFieldReader();

  private StringFieldReader()
  {
  }

  @Override
  public final String convert(final String value)
  {
    return value;
  }
}
