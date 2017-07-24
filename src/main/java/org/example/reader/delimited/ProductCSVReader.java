package org.example.reader.delimited;

import org.example.domain.Product;

public final class ProductCSVReader extends DelimitedDataReader<Product>
{
  public static final ProductCSVReader INSTANCE = new ProductCSVReader();

  private ProductCSVReader()
  {
    super(Product.class);
  }
}
