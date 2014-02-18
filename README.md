# amazigrade

A Clojure library designed to help teachers with their grading

## Usage

Simply plug your values into the data.clj file and then use the functions in core.clj as needed. In this example we are using predefined functions and bindings to transform the info from the data file and repackage it into a way that we can easily copy and paste into an excel file. The 0.5 is a coefficient that determines how drastically the data is transformed. Play and have fun!



    (->> (bell-curve final-grades 0.5)
         (map #(+ (- 75 (class-average final-grades)) %))
         (map round)
         (map int))



If this is not clear, please read the comments in the data.clj and core.clj files.    

## License

Copyright © 2014

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
