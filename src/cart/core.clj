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


(defn cart-item-add [cart item]
  (update-in cart [:items]
    (fn [items]
      (assoc items (item :productId) item))))
