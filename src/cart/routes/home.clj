(ns cart.routes.home
  (:require [compojure.core :refer :all]
            [hiccup.element :only (link-to unordered-list)]
            [cart.views.layout :as layout]))



(def
  productList
  [{:productId 12345 :name "Product 12345"}
   {:productId 23145 :name "Product 23145"}
   {:productId 12453 :name "Product 12453"}
   {:productId 24153 :name "Product 24153"}
   {:productId 13425 :name "Product 13425"}])

(defn home []
  (layout/common
    [:h1 "Hello World!" ]
    [:ul
     (for
       [product productList]
       [:li (link-to (str "/product/" (product :productId)) (product :name))])]))

(defroutes home-routes
  (GET "/" [] (home)))
