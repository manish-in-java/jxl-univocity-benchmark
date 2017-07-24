package org.example.domain;

import com.univocity.parsers.annotations.Parsed;

public class Product
{
  @Parsed(field = FieldTitles.CODE)
  private String code;
  @Parsed(field = FieldTitles.DESCRIPTION)
  private String description;
  @Parsed(field = FieldTitles.NAME)
  private String name;

  public String getCode()
  {
    return code;
  }

  public String getDescription()
  {
    return description;
  }

  public String getName()
  {
    return name;
  }

  public void setCode(String code)
  {
    this.code = code;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public static final class FieldNames
  {
    public static final String CODE        = "code";
    public static final String DESCRIPTION = "description";
    public static final String NAME        = "name";
  }

  public static final class FieldTitles
  {
    public static final String CODE        = "Code";
    public static final String DESCRIPTION = "Description";
    public static final String NAME        = "Name";
  }
}
