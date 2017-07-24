# Overview

[Java Excel API](http://jexcelapi.sourceforge.net) (jExcelAPI) is a Java
library for reading data from Microsoft Excel workbooks. It supports the
`.xls` Excel format.

[uniVocity parsers](https://github.com/uniVocity/univocity-parsers) is a
Java library for reading tabular data from text files. It supports the
popular comma-separated (`.csv`), tab-separated (`.tsv`) and fixed-width
(`.csv` or `.txt`) formats.

There are times when a decision needs to be made at design time on
whether an application should support reading data from `.xls` or `.csv`
format (or both). It then becomes useful to understand the performance
implications of both formats.

This application uses [JMH](http://openjdk.java.net/projects/code-tools/jmh/)
to benchmark the performance of jExcelAPI and uniVocity parsers. It
attempts to read a `.xls` and a `.csv` with exactly the same data to
measure the time taken to read the entire data.

# Usage

mvn clean package; java -jar target/benchmark.jar

# License

This application and its associated source code in its entirety is being made available under the following licensing terms.

    Permission is hereby granted, free of charge, to any person obtaining a copy of
    this software and associated documentation files (the "Software"), to deal in the
    Software without restriction, including without limitation the rights to use, copy,
    modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
    and to permit persons to whom the Software is furnished to do so, subject to the
    following conditions:

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
    INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
    PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
    HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
    CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
    OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
