package org.example.benchmark;

import org.example.domain.Product;
import org.example.reader.delimited.ProductCSVReader;

import java.util.List;

public class ProductCSVReaderBenchmark extends ProductDataReaderBenchmark
{
  List<Product> read()
  {
    return ProductCSVReader.INSTANCE.getRecords(getClass().getClassLoader().getResourceAsStream("Products.csv"));
  }
}
