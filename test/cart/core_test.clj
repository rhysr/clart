(ns cart.core-test
  (:require [clojure.test :refer :all]
            [cart.core :refer :all]))


(deftest creating-cart
  (def cart (create-cart))
  (testing "new cart has"
    (is (true? (contains? cart :items)))
    (is (true? (empty? (cart :items))))
    ))

(deftest counting-cart-lines
  (def cart (create-cart))
  (testing "empty"
    (testing "has zero items"
      (is (= 0 (cart-line-count cart))))))


(deftest add-items-to-cart
  (def cart (create-cart))
  (testing "add item to empty cart"
    (let
      [item {:productId 12345 :quantity 1}
       populated-cart (cart-item-add cart item)]
      (is (true? (contains? populated-cart :items)))
      (is (= 1 (cart-line-count populated-cart)))
      (let
        [items (populated-cart :items)]
        (is (true? (contains? items 12345)))
        (is (= 1 ((items 12345) :quantity))))))

  (testing "add different items to cart"
    (let
      [one-item-cart (cart-item-add cart {:productId 12345 :quantity 1})
       cart (cart-item-add one-item-cart {:productId 23 :quantity 1})
       items (cart :items)]
      (is (= 2 (cart-line-count cart)))
      (is (true? (contains? items 12345)))
      (is (true? (contains? items 23)))
    ))

  (testing "add same item to cart increments quantity"
    (let
      [item {:productId 12345 :quantity 1}
       cart (cart-item-add (cart-item-add (create-cart) item) item)]
      (is (= 1 (cart-line-count cart)))
      (is (true? (contains? cart :items)))
      (let [items (cart :items)]
        (is (true? (contains? items 12345)))
        (let [new-item (items 12345)]
          (is (true? (contains? new-item :quantity)))
          (is (= 2 (new-item :quantity))))))))
