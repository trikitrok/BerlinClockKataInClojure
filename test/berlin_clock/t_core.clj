(ns berlin_clock.t-core
  (:use midje.sweet)
  (:use [berlin_clock.core]))

(use '[clojure.string :only (join)])

(facts "about Berlin Clock"
       (facts "seconds lamp"
              (fact "It turns off for one second (Y)"
                    (show "00:00:00") => (join "\n"
                                               ["Y"
                                                "OOOO"
                                                "OOOO"
                                                "OOOOOOOOOOO"
                                                "OOOO"]))

              (fact "It turns on for the next second (R)"
                    (show "00:00:01") => (join "\n"
                                               ["R"
                                                "OOOO"
                                                "OOOO"
                                                "OOOOOOOOOOO"
                                                "OOOO"]))))
