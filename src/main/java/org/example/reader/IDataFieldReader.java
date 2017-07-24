/*
 * See the file license.txt in the main project folder
 * for license and other legal information.
 */

package org.example.reader;

import java.io.Serializable;

public interface IDataFieldReader<T extends Serializable>
{
  T get(final Object value);

  T get(final Object value, final T defaultValue);
}
