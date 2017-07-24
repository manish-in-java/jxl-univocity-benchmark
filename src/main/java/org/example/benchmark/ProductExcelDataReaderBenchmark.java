package org.example.benchmark;

import org.example.domain.Product;
import org.example.reader.excel.ProductExcelDataReader;

import java.util.List;

public class ProductExcelDataReaderBenchmark extends ProductDataReaderBenchmark
{
  List<Product> read()
  {
    return ProductExcelDataReader.INSTANCE.getRecords(getClass().getClassLoader().getResourceAsStream("Products.xls"));
  }
}
