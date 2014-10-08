(ns cart.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))



(defn cart-factory []
  {:items {}}
  )


(defn cart-line-count [cart]
  (count (cart :items)))
