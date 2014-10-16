(ns cart.core
  (:gen-class))

(defn valid-cart? [cart]
  (if (not (contains? cart :items))
    false
    true))

(defn valid-product-id? [product-id]
  (and (integer? product-id) (> product-id 0)))


(defn valid-quantity? [quantity]
  (and (integer? quantity) (> quantity 0)))


(defn valid-line-item? [line-item]
  (if (not (and (contains? line-item :product-id) (valid-product-id? (line-item :product-id))))
    false
    (if (not (and (contains? line-item :quantity) (valid-quantity? (line-item :quantity))))
      false
      true)))

(defn create-cart []
  {:items {}}
  )


(defn cart-line-count [cart]
  {:pre [(valid-cart? cart)]}
  (count (cart :items)))


(defn cart-item-add [cart item]
  {:pre [(valid-cart? cart)
         (valid-line-item? item)]}
  (let [product-id (item :product-id)]
    (update-in cart [:items]
      (fn [items]
        (if (contains? items product-id)
          (update-in items [product-id]
            (fn [current-item]
              (update-in current-item [:quantity]
                (fn [quantity] (+ quantity (item :quantity))))))
          (assoc items product-id item))))))


