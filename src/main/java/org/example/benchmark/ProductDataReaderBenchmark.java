package org.example.benchmark;

import org.example.domain.Product;
import org.openjdk.jmh.annotations.*;

import java.util.List;

public abstract class ProductDataReaderBenchmark
{
  @Benchmark
  @BenchmarkMode(Mode.AverageTime)
  @Fork(0)
  @Measurement(iterations = 5)
  @Warmup(iterations = 3)
  public void benchmark()
  {
    final List<Product> products = read();

    assert products != null;
    assert products.size() > 0;

    products.forEach(product -> {
      assert product != null;
      assert product.getCode() != null;
      assert product.getDescription() != null;
      assert product.getName() != null;
    });
  }

  abstract List<Product> read();
}
