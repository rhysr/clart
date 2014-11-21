(ns cart.views.layout
  (:require [hiccup.page :refer [html5 include-css include-js]]))

(defn common [& body]
  (html5
    [:head
     [:meta {:charset "utf-8"}]
     [:meta {:content "IE=edge" :http-equiv "X-UA-Compatible"}]
     [:meta {:content "width=device-width, initial-scale=1" :name "viewport"}]
     [:title "Welcome to cart"]
     (include-css
       "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css"
       "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css"
       "/css/cart.css")]
    [:body
     [:nav {:role "navigation" :class "navbar navbar-inverse navbar-fixed-top"}
      [:div {:class "container"}
       [:div {:class "navbar-header"}
        [:button {:aria-controls "navbar" :aria-expanded "false" :data-target "#navbar" :data-toggle "collapse" :class "navbar-toggle collapsed" :type "button"}
          [:span {:class "sr-only"} "Toggle Navigation"]
          [:span {:class "icon-bar"}]
          [:span {:class "icon-bar"}]
          [:span {:class "icon-bar"}] ]
        [:a {:href "#" :class "navbar-brand"} "Cart"] ]
       [:div {:class "collapse navbar-collapse" :id "navbar"}
        [:ul {:class "nav navbar-nav"}
         [:li {:class "active"}
          [:a {:href "#"} "Home"]]
         [:li [:a {:href "#about"} "About"]]
         [:li [:a {:href "#contact"} "Contact"]]]]]]
     body
     (include-js
       "https://code.jquery.com/jquery-2.1.1.min.js"
       "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js") ] ))
