﻿!function(e){"use strict";function o(){e(".preloader").length&&e(".preloader").delay(300).fadeOut(500)}e(".carousel").carousel({pause:"false",interval:5e3}),window.addEventListener("scroll",function(e){var o=window.pageYOffset||document.documentElement.scrollTop,l=document.querySelector("header");o>150?classie.add(l,"smaller"):classie.has(l,"smaller")&&classie.remove(l,"smaller")}),e(".scrollingto-fixed").scrollToFixed();var l=e(".summary");l.each(function(o){var t=e(l[o]),a=l[o+1];t.scrollToFixed({marginTop:e(".scrollingto-fixed").outerHeight(!0)+10,limit:function(){return a?e(a).offset().top-e(this).outerHeight(!0)-10:e(".footer").offset().top-e(this).outerHeight(!0)-10},zIndex:999})}),e(".popup-gallery").magnificPopup({delegate:"a",type:"image",tLoading:"Loading image #%curr%...",mainClass:"mfp-img-mobile",gallery:{enabled:!0,navigateByImgClick:!0,preload:[0,1]},image:{tError:'<a href="%url%">The image #%curr%</a> could not be loaded.',titleSrc:function(e){return e.el.attr("title")+"<small>by Marsel Van Oosten</small>"}}}),e(".lightbox-image").length&&e(".lightbox-image").fancybox(),e(".player").length&&e(".player").mb_YTPlayer(),e(".accordion-box").length&&e(".accordion-box .acc-btn").on("click",function(){!0!==e(this).hasClass("active")&&e(".accordion-box .acc-btn").removeClass("active"),e(this).next(".acc-content").is(":visible")?(e(this).removeClass("active"),e(this).next(".acc-content").slideUp(500)):(e(this).addClass("active"),e(".accordion-box .acc-content").slideUp(500),e(this).next(".acc-content").slideDown(500))}),e(".testimonial-carousel").length&&e(".testimonial-carousel").owlCarousel({loop:!0,margin:30,dots:!0,nav:!1,autoplayHoverPause:!1,autoplay:!0,smartSpeed:700,responsive:{0:{items:1,center:!1},480:{items:1,center:!1},600:{items:1,center:!1},768:{items:1},992:{items:1},1200:{items:1}}}),e(function(){e.scrollUp({scrollName:"scrollUp",scrollDistance:300,scrollFrom:"top",scrollSpeed:700,easingType:"linear",animation:"fade",animationSpeed:200,scrollTrigger:!1,scrollTarget:!1,scrollImg:!0,scrollText:"",scrollTitle:!1,scrollImg:!1,activeOverlay:!1,zIndex:2147483647})}),e(window).on("load",function(){o()}),e(window).on("resize",function(){})}(window.jQuery);
