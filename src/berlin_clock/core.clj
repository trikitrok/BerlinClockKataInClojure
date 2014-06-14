(ns berlin_clock.core)

(use '[clojure.string :only (join)])

(defn show [time]
  (join "\n"
        ["Y"
         "OOOO"
         "OOOO"
         "OOOOOOOOOOO"
         "OOOO"]))
