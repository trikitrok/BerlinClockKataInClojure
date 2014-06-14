(ns berlin_clock.t-core
  (:use midje.sweet)
  (:use [berlin_clock.core]))

(use '[clojure.string :only (join)])

(facts "about Berlin Clock"

  (fact "it tells the time in a funny way"
    (show "00:00:00") => (join "\n"
                               ["Y"
                               "OOOO"
                               "OOOO"
                               "OOOOOOOOOOO"
                               "OOOO"])))
