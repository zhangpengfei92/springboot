layui.define([ 'jquery'], function(exports) {
    var $=layui.jquery;

    var obj = {
        init : function(sobj) {
            var websocket;
            if ('WebSocket' in window) {
                websocket = new WebSocket(sobj.wsUrl);
            } else if ('MozWebSocket' in window) {
                websocket = new MozWebSocket(sobj.wsUrl);
            } else {
                websocket = new SockJS(sobj.wsUrl);
            }
            websocket.onopen = function(evn) {

               websocket.send(sobj.userId);
            };
            websocket.onmessage = function(evn) {
               eval('var data='+evn.data);
               $('#dbmoney').html(data.money.toFixed(2));
            };
            websocket.onclose = function() {
                clearInterval(inter);
            };
            var inter=    setInterval(function(){
                websocket.send(sobj.userId);
            },sobj.timeout);
        }
    }

    exports('socket', obj);
});
