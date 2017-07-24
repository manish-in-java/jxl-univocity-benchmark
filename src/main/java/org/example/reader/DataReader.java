package org.example.reader;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public abstract class DataReader<RecordType>
{
  protected final RecordType getRecord(final Map<? extends String, Object> properties)
  {
    final RecordType record = this.getRecord();

    final BeanWrapper wrapper = new BeanWrapperImpl(record);

    for (final String property : properties.keySet())
    {
      wrapper.setPropertyValue(property, properties.get(property));
    }

    return record;
  }

  public abstract List<RecordType> getRecords(final InputStream stream);

  protected abstract RecordType getRecord();
}
