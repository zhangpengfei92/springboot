(function ($) {
    var methods = {};

    var loading = '<div id="loadgif" style="width:120px;height:90px;position:absolute;top:50%;left:50%;">\n' +
        '　　<img width="100%" alt="加载中..." src="images/loading.gif"/>\n' +
        '</div>';
    var data = [];
    var config = {
        page: 1,
        limit: 5,
        count: 0,
        leftheight: 0,
        hotdata: []
    };
    /**
     * 模块查询
     * @param dom
     */
    $.aisPc = function (dom) {
        var obj = $.extend({}, config, dom);
        config = obj;
        //初始化方法
        initMethods();
        init();

        $('#pcleft').on('click', 'li', function () {

            methods.drawRightLi($(this).attr('pcid'));
        });

        $('#leftscroll').on('click', '#pcbanner', function () {

            methods.drawRightLi($('#pcbanner').attr('pchotid'));
        });

        $('#leftscroll').scroll(function () {
            console.log("A", config.leftheight);
            console.log("B", $(this).scrollTop());

            if (config.leftheight == $(this).scrollTop()) {
                methods.goPage();
            }
        });
    }

    /**
     * 调查问卷
     * @param dom
     */
    $.aisRiskEva = function (dom) {

        $('#riskok').click(function () {

            var total=0;
            for(var i=1;i<17;i++){
                var val = $('input[name=choice'+i+']:checked').val();
                if(val==undefined){
                    alert('请填写问卷');
                    return;
                }
                if(val=='1'){
                    total+=10;
                }
                if(val=='2'){
                    total+=8;
                }
                if(val=='3'){
                    total+=5;
                }
                if(val=='4'){
                    total+=3;
                }
                if(val=='5'){
                    total+=0;
                }

            }




            $.post('/aisuser/getRiskTotal', {
                online: dom.online,
                aisUserRisktext: total
            }, function (res) {
                if (res.code == '200') {
                    location.href = '/static/pc/center/risk_result.html?result=' + total;
                } else {
                    alert(res.msg);
                }
            });
        });


        $.post('/aisuser/risk', dom, function (res) {

            if (res.code == '200') {

            }
            if (res.code == '401') {
                alert(res.msg);
                window.close();
            }
            if (res.code == '402') {
                location.href = '/static/pc/center/risk_result.html?result=' + res.data.aisUserRisktext;
            }
        });


    }

    /**
     * 会员支付
     */
    $.aisPay = function (dom) {
        function getAuthName(n) {
            if (n == 0) {
                return '热点题材';
            }
            if (n == 1) {
                return '每日涨停';
            }
            if (n == 2) {
                return '波段操作';
            }
            if (n == 3) {
                return '白马股';
            }
            if (n == 4) {
                return '短线稳健';
            }
            if (n == 5) {
                return '细分行业';
            }
            if (n == 6) {
                return '特殊形态';
            }
        }


        $.post('/aisuser/getByOnline', dom, function (res) {
            if (res != '') {
                var prid = res.aisUserPrivilegeid;
                if (prid != null) {
                    $.post('/aisprivilege/getByKey', {key: prid}, function (ress) {

                        var date = new Date(res.aisUserPrivlegeidendtime);
                        var y = date.getFullYear();
                        var M = date.getMonth() + 1;
                        var d = date.getDate();

                        var str = y + "-" + M + "-" + d;
                        if (str == "1970-1-1") {
                            str = '';
                        }
                        var authstr = ress.aisPrivilegeName + '&nbsp;(';
                        if (ress.aisPrivilegeInclude != '' && ress.aisPrivilegeInclude != null) {
                            if (ress.aisPrivilegeInclude.indexOf(',') != -1) {
                                var dataress = ress.aisPrivilegeInclude.split(',');
                                for (var i = 0; i < dataress.length; i++) {
                                    if (dataress.length - 1 == i) {
                                        authstr += '&nbsp;' + getAuthName(dataress[i]);
                                    } else {
                                        authstr += '&nbsp;' + getAuthName(dataress[i]) + '、';
                                    }
                                }
                            } else {
                                authstr += '&nbsp;' + getAuthName(ress.aisPrivilegeInclude);
                            }
                        }
                        authstr += ')';

                        $('#contact').html(authstr + '<span class="time">&nbsp;&nbsp;&nbsp;&nbsp;到期时间&nbsp;' + str + '</span>');
                    });
                }
            } else {
                alert('请先登录');
            }
        });

        $.post('/aisprivilege/selectAll', {}, function (res) {
            var options = '<option value ="">请选择</option>';
            if (res.length != 0) {
                for (var i = 0; i < res.length; i++) {
                    options += '<option value ="' + res[i].id + '">' + res[i].aisPrivilegeName + '</option>';
                }
            }
            $('#selectHy').html(options);
            $('#selectHy').on('change', function () {

                var sindex = $(this).val();
                $('#sfbz').val('');
                $('#zfje').val('');
                if (sindex == '') {

                } else {
                    for (var i = 0; i < res.length; i++) {
                        if (res[i].id == sindex) {

                            var dd = res[i].aisUserContract.replace(/<\/?.+?>/g, "");
                            var dds = dd.replace(/&nbsp;/g, "");
                            $('#sfbz').val(dds);
                            $('#zfje').val(res[i].aisPrivilegePrice + '元');
                            break;
                        }
                    }
                }

            });
        });

        $('.zhifu').click(function () {

            var aisOrderPrivname = $('#selectHy option:selected').val();
            if (aisOrderPrivname == '') {
                alert('请选择会员');
                return;
            }
            var describe = $('#sfbz').val();
            if (describe == '') {
                alert('未有收费标准');
                return;
            }
            var aisOrderPaygold = $('#zfje').val();
            if (aisOrderPaygold == '') {
                alert('未有支付金额');
                return;
            }
            var aisUserNo = $('#name').val();
            if (aisUserNo == '') {
                alert('姓名不能为空');
                return;
            }

            var aisUserPhone = $('#phone').val();
            if (aisUserPhone==''){
                alert('手机号不能为空');
                return;
            }

            var aisOrderPay = $('.up').html();
            if(aisOrderPay=='支付宝支付'){
                var aipay = $('#aipay').val();
                if(aipay==''){
                    alert('请填写支付宝账号');
                    return;
                }
                aisOrderPay=aisOrderPay+aipay;
            }
            if(aisOrderPay=='微信支付'){
                var wechatpay= $('#wechatpay').val();
                if(wechatpay==''){
                    alert('请填写微信账号');
                    return;
                }
                aisOrderPay=aisOrderPay+wechatpay;
            }
            if(aisOrderPay=='银行卡支付'){
                var bankpay = $('#bankpay').val();
                if(bankpay==''){
                    alert('请填写银行账号');
                    return;
                }
                aisOrderPay=aisOrderPay+bankpay;
            }

            var obj = {
                pid: aisOrderPrivname,
                aisUserPhone: aisUserPhone,
                online:dom.online,
                aisOrderPay:aisOrderPay
            };

            var desc = {
                pid: aisOrderPrivname,
                aisUserPhone: aisUserPhone,
                online:dom.online,
                aisUserNo:aisUserNo,
                aisUserPhone:aisUserPhone
            }


            obj.detail=JSON.stringify(desc);
            $.post('/aisorder/add',obj,function(res){
                if(res.code=='200'){
                    alert('提交成功');
                    window.close();
                }else{
                    alert(res.msg);
                }
            });
        });

    }

    /**
     * 智能诊断
     */
    $.aisDiagnose=function(){

        var $list=$('#list');

        $('.button1').click(function(){
           var len= $('#list li').length;
           if(len==0){
               alert('暂无数据~');
               return;
           }

           if(len>1){
               alert('多条数据请点击下方提示跳转');
               return;
           }
            location.href='smart_res.html?id='+ $('#list li').attr('es');

        });


        $('#list').on('click','li',function(){

           location.href='smart_res.html?id='+ $(this).attr('es');

        });


        var $input = $('.search-text');
        $input.keyup(function () {
            //获取输入的内容
            var sVal = $(this).val();

            $.ajax({
                url: '/serach/getDate',
                type: 'get',
                dataType: 'json',
                data: {wd: sVal}
            })
                .done(function (data) {

                    //对列表进行遍历取出里面的值
                    var str = '';
                    for (var index = 0; index < data.length; index++) {
                        str += '<li es="'+data[index].id+'"> <span style="width: 200px;display: inline-block">股票名称:' +data[index].title +'</span>股票代码:'+data[index].aisIntelligentinvestmentCode+ '</li>';
                    }
                    $list.html(str);
                })
                .fail(function (data) {
                    return;
                });

        })
        }

    /**
     * 测评结果
     * @param dom
     */
    $.aisRiskResult = function (dom) {
        var total = dom.result;
        var str='';
        if(total<50){
            str='<br>\n' +
                '\n' +
                '属于保守型投资者(可以选择投资学习培训服务)';
        }
        if(total>=50&&total<70){
            str='<br>\n' +
                '\n' +
                '属于稳健型型投资者(可以选择初级会员服务)';
        }
        if(total>=70&&total<100){
            str='<br>\n' +
                '\n' +
                '属于平衡型投资者(可以选择中级会员服务)';
        }
        if(total>=100&&total<130){
            str='<br>\n' +
                '\n' +
                '属于成长型投资者(可以选择高级会员投资服务)';
        }
        if(total>=130&&total<=160){
            str='<br>\n' +
                '\n' +
                '属于进取型投资者(可以选择VIP超激进投资服务)';
        }

        $('.zhpf').html('您的综合得分为：' + dom.result +str );
    }

    /**
     * 会员合同
     * @param dom
     */
    $.aisAa = function (dom) {
        $.post('/aisuser/getHt', dom, function (res) {
            console.log(res);
            $('.sad').html(res);
        });
    }

    /**
     * 个人中心用户
     * @param dom
     */
    $.aisCenterUser = function (dom) {
        var index = 60;

        var ismsg = false;

        $.post('/aisuser/getByOnline', dom, function (res) {
            console.log(res);
            config = res;
            $('.phonenumber').html(res.aisUserPhone.substr(0, 3) + '****' + res.aisUserPhone.substr(7));
        });

        $('.bindPhone').on('click', 'input', function () {
            var phone = $('.phone').val();
            if (phone == '') {
                alert('请填写手机号');
                return;
            }
            var code = $('.code').val();
            if (code == '') {
                alert('请填写验证码');
                return;
            }
            if (ismsg) {
                $.post('/aisuser/updatePhone', {
                    phone: phone,
                    code: code,
                    aisUserNo: config.aisUserNo
                }, function (res) {
                    if (res.code = '200') {
                        alert('绑定成功');
                        $('.close').click();
                    } else {
                        alert(res.msg);
                    }
                });
            } else {
                alert('请点击发送验证码');
            }
        });
        $('#sendMsg').click(function () {
            var phone = $('.phone').val();
            if (phone != '') {

                if (index == 60) {
                    ismsg = true;
                    $.post('/aisuser/sendMsg', {phone: phone}, function (res) {
                        if (res.code == '200') {

                            var inter = setInterval(function () {
                                if (--index == 0) {
                                    index = 60;
                                    $('#sendMsg').html('获取验证码');
                                    clearInterval(inter);
                                } else {
                                    $('#sendMsg').html(index + '秒');
                                }

                            }, 1000);
                        } else {
                            alert(res.msg);
                        }
                    });
                } else {
                    alert('短信已发送');
                }

            } else {
                alert('请填写手机号');
            }

        });
        $('.pwdupdate').on('click', 'input', function () {
            var pwd = $('.newpwd').val();
            var repwd = $('.renewpwd').val();
            if (pwd == '') {
                alert('新密码不能为空');
                return;
            }
            if (repwd == '') {
                alert('确认密码不能为空');
                return;
            }
            if (repwd != pwd) {
                alert('确认密码与新密码不一致');
                return;
            }
            $.post('/aisuser/updatepwd', {
                aisUserNo: config.aisUserNo,
                aisUserPassword: pwd,
                reAisUserPassword: repwd
            }, function (res) {
                if (res.code == '200') {
                    alert('修改成功');
                } else {
                    alert(res.msg);

                }
            });
        });

    }

    $.disclaimer = function () {
        $.post('/aisriskwarning/findone', {}, function (res) {
            $('.ddr').html(res.content);
        });
    }

    /**
     * 有偿建议
     * @param dom
     */
    $.aisSuggest = function (dom) {
        $('.present').click(function () {
            var content = $('.textwriting textarea').val();
            if (content == '') {
                alert('请填写建议');
                return;
            }
            $.post('/aispaymentadvice/send', {
                content: content,
                online: dom.online
            }, function (res) {
                if (res.code == '200') {
                    alert('成功');
                } else {
                    alert(res.msg);
                }
            });
        });

    }

    $.GetQueryString = function (name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    }

    function initMethods() {
        $('body').append(loading);
        $("#loadgif").hide();
        methods.formatterdata = function (value) {
            var date = new Date(value);
            var y = date.getFullYear();
            var M = date.getMonth() + 1;
            var d = date.getDate();
            var h = date.getHours();
            var m = date.getMinutes();
            var s = date.getSeconds();
            var str = y + "-" + M + "-" + d + " " + h + ":" + m + ":" + s;
            if (str == "1970-1-1 8:0:0")
                return "";
            return str;
        };
        methods.drawleftLi = function ($data) {
            if (config.module == 'invest_study') {
                var lefth5 = '';
                if ($data.length > 0) {
                    for (var i = 0; i < $data.length; i++) {
                        lefth5 += '<li pcid="' + $data[i].id + '">';
                        lefth5 += '<div class="learn">';
                        lefth5 += '<a href="javascript:void(0)" target="learn right">';
                        lefth5 += '<img src="' + $data[i].aisStudylnvestmentIcon + '" width="230px" height="150px">';
                        lefth5 += '<div class="learntext">';
                        lefth5 += '<h3>' + $data[i].aisStudylnvestmentTitle + '</h3>';
                        lefth5 += '<p></p>';
                        lefth5 += '<i>' + methods.formatterdata($data[i].aisStudylnvestmentUpdatetime) + '</i>';
                        lefth5 += '</div>';
                        lefth5 += '<div style="clear: both"></div>';
                        lefth5 += '</a></div></li>';
                    }
                } else {
                    lefth5 += '<span style="color:#ffffff">暂无数据~</span>';
                }
                $('#pcleft').append(lefth5);
            }
            if (config.module == 'smart_grind') {
                var lefth5 = '';
                if ($data.length > 0) {
                    for (var i = 0; i < $data.length; i++) {
                        lefth5 += '<li pcid="' + $data[i].id + '">';
                        lefth5 += '<div class="learn">';
                        lefth5 += '<a href="javascript:void(0)" target="learn right">';
                        lefth5 += '<img src="' + $data[i].aisIntelligentresearchIcon + '" width="230px" height="150px">';
                        lefth5 += '<div class="learntext">';
                        lefth5 += '<h3>' + $data[i].aisIntelligentresearchTitle + '</h3>';
                        lefth5 += '<p></p>';
                        lefth5 += '<i>' + methods.formatterdata($data[i].aisIntelligentresearchUpdatetime) + '</i>';
                        lefth5 += '</div>';
                        lefth5 += '<div style="clear: both"></div>';
                        lefth5 += '</a></div></li>';
                    }
                } else {
                    lefth5 += '<span style="color:#ffffff">暂无数据~</span>';
                }
                $('#pcleft').append(lefth5);
            }
            if (config.module == 'smart_info') {
                var lefth5 = '';
                if ($data.length > 0) {
                    for (var i = 0; i < $data.length; i++) {

                        lefth5 += '<li pcid="' + $data[i].id + '">';
                        lefth5 += '<div class="news">';
                        lefth5 += '<a href="javascript:void(0)" target="new right">';
                        lefth5 += '<img src="' + $data[i].aisIntelligentinformationIcon + '" width="140px" height="105px"/>';
                        lefth5 += '<div class="newtext">';
                        lefth5 += '<h3>' + $data[i].aisIntelligentinformationTitle + '</h3>';
                        lefth5 += '<p>' + methods.formatterdata($data[i].aisIntelligentinformationUpdatetime);
                        if ($data[i].aisIntelligentinformationHot == 1) {
                            lefth5 += '<i class="hot">热点</i>';
                        } else {
                            lefth5 += '<i >&nbsp;&nbsp;&nbsp;&nbsp;</i>';
                        }
                        lefth5 += '</p>';
                        lefth5 += '</div><div style="clear: both"></div>\n' +
                            '\t\t\t\t\t</a>\n' +
                            '\t\t\t\t</div>\n' +
                            '\t\t\t</li>';
                    }

                }
                $('#pcleft').append(lefth5);
            }

        };
        methods.drawRightLi = function (index) {
            if (config.module == 'invest_study') {
                if (data.length > 0) {
                    var html = '';
                    var datum;
                    for (var i = 0; i < data.length; i++) {
                        if (data[i].id == index) {
                            datum = data[i];
                            break;
                        }
                    }


                    html += '<h2 >' + datum.aisStudylnvestmentTitle + '</h2>';
                    html += '<p class="time">' + methods.formatterdata(datum.aisStudylnvestmentUpdatetime) + '</p>';
                    html += '<div class="line"></div>';
                    html += '<div style="font-size: 1rem;color:#FFFFFF">' + datum.aisStudylnvestmentText + '</div>';

                    $('#rightpc').html(html);
                }
            }
            if (config.module == 'smart_grind') {
                if (data.length > 0) {
                    var html = '';
                    var datum;
                    for (var i = 0; i < data.length; i++) {
                        if (data[i].id == index) {
                            datum = data[i];
                            break;
                        }
                    }
                    html += '<h2 >' + datum.aisIntelligentresearchTitle + '</h2>';
                    html += '<p class="time">' + methods.formatterdata(datum.aisIntelligentresearchUpdatetime) + '</p>';
                    html += '<div class="line"></div>';
                    html += '<div style="font-size: 1rem;color:#FFFFFF">' + datum.aisIntelligentresearchText + '</div>';
                    $('#rightpc').html(html);
                }
            }
            if (config.module == 'smart_info') {
                if (data.length > 0) {
                    var html = '';
                    var datum;
                    for (var i = 0; i < data.length; i++) {
                        if (data[i].id == index) {
                            datum = data[i];
                            break;
                        }
                    }
                    if (datum == undefined) {
                        datum = config.hotdata;
                    }
                    html += '<h2 >' + datum.aisIntelligentinformationTitle + '</h2>';
                    if (datum.aisIntelligentinformationHot == 1) {
                        html += '<p class="time">' + methods.formatterdata(datum.aisIntelligentinformationUpdatetime) + '<i class="hot">热点</i></p>';
                    } else {
                        html += '<p class="time">' + methods.formatterdata(datum.aisIntelligentinformationUpdatetime) + '</p>';
                    }

                    html += '<div class="line"></div>';
                    html += '<div style="font-size: 1rem;color:#FFFFFF">' + datum.aisIntelligentinformationText + '</div>';
                    $('#rightpc').html(html);
                }
            }
        };
        methods.goPage = function () {

            if (config.count > config.limit * (config.page)) {
                $("#loadgif").show();
                config.serach.page = config.page + 1;
                $.post(config.url, config.serach, function (res) {
                    for (var i = 0; i < res.data.length; i++) {
                        data.push(res.data[i]);
                    }
                    config.page = config.page + 1;
                    methods.drawleftLi(res.data);
                    var height = $('#pcleft li').height();
                    var totalheight = $('#leftscroll').height();


                    if ($('#pcbanner').height() != null) {
                        config.leftheight = (data.length * height) - totalheight + 240;
                    } else {
                        config.leftheight = (data.length * height) - totalheight;
                    }
                    $("#loadgif").hide();
                })
            } else {
                $('.pcbind span').html('没有数据了^_^');
            }
        }
    }

    function init() {

        config.serach.page = config.page;
        config.serach.limit = config.limit;
        $.post(config.url, config.serach, function (res) {
            config.count = res.count;
            if (config.count != 0) {
                data = res.data;
            } else {
                data = [];
            }
            methods.drawRightLi(data[0].id);
            methods.drawleftLi(data);
            var height = $('#pcleft li').height();
            var totalheight = $('#leftscroll').height();
            if ($('#pcbanner').height() != null) {
                config.leftheight = (data.length * height) - totalheight + 240;

                $.post("/aisintelligentinformation/getHot",
                    config.serach
                    , function (res) {

                        if (res.length == 1) {
                            var hotdata = res[0];
                            $('#pcbanner img').attr('src', hotdata.aisIntelligentinformationIcon);
                            $('.bannertext').html(hotdata.aisIntelligentinformationTitle);
                            $('#pcbanner').attr('pchotid', hotdata.id);
                            config.hotdata = res[0];
                        }
                        if (res.length == 0) {
                            $('#pcbanner img').attr('src', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1539922328113&di=0bb4845b6152905182c076a2448c5b63&imgtype=0&src=http%3A%2F%2Fwww.corp001.com%2Ftemplates%2Fdefault%2Fshop%2Fimages%2Ferrors.jpg');
                            $('.bannertext').html('');
                        }
                    });
            } else {
                config.leftheight = (data.length * height) - totalheight;
            }
        });

    }
})(jQuery)
