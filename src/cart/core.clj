(ns cart.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))



(defn create-cart []
  {:items {}}
  )


(defn cart-line-count [cart]
  (count (cart :items)))


(defn cart-item-add [cart item]
  (let [productId (item :productId)]
    (update-in cart [:items]
      (fn [items]
        (if (contains? items productId)
          (update-in items [productId]
            (fn [current-item]
              (update-in current-item [:quantity]
                (fn [quantity] (+ quantity (item :quantity))))))
          (assoc items productId item))))))
