# amazigrade

A Clojure library designed to help teachers with their grading
## Usage

After requiring the library you can simply plug in your percentify vector into the following final-grades definition and then map a couple functions to your data, depending on what you want to get returned. In the following example, 

the code in part 1 casts vector of desired weights to each of the grades in a predefined "grades" vector in the external "data" file. In part 2 we map the class-finals onto the offical class-list to reorder the data as needed. Finally, the round function is called on this new list to return the final grades as integers that will be submitted to the students.  


part 1

    (def final-grades
    (->> data/grades (map (partial percentify-vector [10.0 20.0 15.0 25.0 30.0])) (mapv #(apply + %))))

part 2

    (map round (map class-finals data/official-nums))

## License

Copyright © 2014 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
