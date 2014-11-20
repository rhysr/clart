(ns cart.test.core
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
      [item {:product-id 12345 :quantity 1 :weight-kg 2}
       populated-cart (cart-item-add cart item)]
      (is (true? (contains? populated-cart :items)))
      (is (= 1 (cart-line-count populated-cart)))
      (let
        [items (populated-cart :items)]
        (is (true? (contains? items 12345)))
        (is (= 1 ((items 12345) :quantity))))))

  (testing "add different items to cart"
    (let
      [one-item-cart (cart-item-add cart {:product-id 12345 :quantity 1 :weight-kg 2})
       cart (cart-item-add one-item-cart {:product-id 23 :quantity 1 :weight-kg 2})
       items (cart :items)]
      (is (= 2 (cart-line-count cart)))
      (is (true? (contains? items 12345)))
      (is (true? (contains? items 23)))
    ))

  (testing "add same product to cart sums quantity"
    (let
      [item-1 {:product-id 12345 :quantity 1 :weight-kg 2}
       item-2 {:product-id 12345 :quantity 3 :weight-kg 2}
       cart (cart-item-add (cart-item-add (create-cart) item-1) item-2)]
      (is (= 1 (cart-line-count cart)))
      (is (true? (contains? cart :items)))
      (let [items (cart :items)]
        (is (true? (contains? items 12345)))
        (let [line-item (items 12345)]
          (is (true? (contains? line-item :quantity)))
          (is (= 4 (line-item :quantity))))))))

(deftest summing-cart-properties
  (testing "empty cart"
    (def cart (create-cart))
    (def properties (cart-items-sum cart))
    (is (true? (contains? properties  :price-ex-tax)))
    (is (= 0.0 (properties :price-ex-tax)))
    (is (true? (contains? properties  :weight-kg)))
    (is (= 0.0 (properties :weight-kg))))
  (testing "populated cart"
    (def cart (cart-item-add
                (cart-item-add
                  (create-cart)
                  {:product-id 12345 :quantity 2 :price-ex-tax 23.05 :weight-kg 3.43})
                {:product-id 3433 :quantity 3 :price-ex-tax 45.34 :weight-kg 1.21}))

    (def properties (cart-items-sum cart))
    (is (= 182.12 (properties :price-ex-tax)))
    (is (= 10.49 (properties :weight-kg)))
    ))

(deftest cart-validation
    (is (false? (valid-cart? {})))
    (is (true? (valid-cart? (create-cart)))))

(deftest line-item-validation
  (is (false? (valid-line-item? {})))
  (is (false? (valid-line-item? {:product-id 12345})))
  (is (false? (valid-line-item? {:product-id -1})))
  (is (false? (valid-line-item? {:product-id -1 :quantity 2})))
  (is (false? (valid-line-item? {:product-id 12345 :quantity -2})))
  (is (true? (valid-line-item? {:product-id 12345 :quantity 2 :weight-kg 1.1}))))


(deftest product-id-validation
  (is (false? (valid-product-id? -1)))
  (is (false? (valid-product-id? 0)))
  (is (false? (valid-product-id? "number")))
  (is (true? (valid-product-id? 12345))))

(deftest quantity-validation
  (is (false? (valid-quantity? -1)))
  (is (false? (valid-quantity? 0)))
  (is (false? (valid-quantity? "number")))
  (is (true? (valid-quantity? 12345))))

(deftest weight-validation
  (is (false? (valid-weight-kg? -1)))
  (is (false? (valid-weight-kg? 0)))
  (is (false? (valid-weight-kg? "number")))
  (is (true? (valid-weight-kg? 12345))))
