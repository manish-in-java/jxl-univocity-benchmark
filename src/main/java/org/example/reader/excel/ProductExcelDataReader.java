package org.example.reader.excel;

import org.example.domain.Product;

public final class ProductExcelDataReader extends ExcelDataReader<Product>
{
  public static final ProductExcelDataReader INSTANCE = new ProductExcelDataReader();

  private ProductExcelDataReader()
  {
    super("Products"
        , 2000
        , new ExcelDataField("B", Product.FieldNames.CODE, Product.FieldTitles.CODE)
        , new ExcelDataField("D", Product.FieldNames.DESCRIPTION, Product.FieldTitles.DESCRIPTION)
        , new ExcelDataField("C", Product.FieldNames.NAME, Product.FieldTitles.NAME));
  }

  @Override
  protected final Product getRecord()
  {
    return new Product();
  }
}
