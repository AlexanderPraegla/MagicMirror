/**
 * Created by Alex on 26.09.2015.
 */
jQuery(document).ready(function ($) {
    var news = [];
    var newsIndex = 0;

    moment.lang(lang);
    (function fetchNews() {
        $.feedToJson({
            feed: 'http://www.tagesschau.de/xml/rss2',
            success: function (data) {
                news = [];
                for (var i in data.item) {
                    var item = data.item[i];
                    news.push(item.title);
                }
            }
        });
        setTimeout(function () {
            fetchNews();
        }, 60000);
    })();

    (function showNews() {
        var newsItem = news[newsIndex];
        $('.news').updateWithText(newsItem, 2000);

        newsIndex--;
        if (newsIndex < 0) newsIndex = news.length - 1;
        setTimeout(function () {
            showNews();
        }, 5500);
    })();

});