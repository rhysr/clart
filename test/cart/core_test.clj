(ns cart.core-test
  (:require [clojure.test :refer :all]
            [cart.core :refer :all]))


(deftest creating-cart
  (def cart (cart-factory))
  (testing "new cart has"
    (is (true? (contains? cart :items)))
    (is (true? (empty? (cart :items))))
    ))

(deftest counting-cart-lines
  (def cart (cart-factory))
  (testing "empty"
    (testing "has zero items"
      (is (= 0 (cart-line-count cart))))))


(deftest add-items-to-cart
  (def cart (cart-factory))
  (testing "add item to empty cart"
    (let
      [item {:productId 12345 :quantity 1}
       populated-cart (cart-item-add cart item)]
      (is (true? (contains? populated-cart :items)))
      (is (= 1 (cart-line-count populated-cart)))
      (let
        [items (populated-cart :items)]
        (is (true? (contains? items 12345)))
        (is (= 1 ((items 12345) :quantity)))))))
