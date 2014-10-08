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
