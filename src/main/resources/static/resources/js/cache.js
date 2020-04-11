var cacheStr = window.sessionStorage.getItem("cache"),
    oneLoginStr = window.sessionStorage.getItem("oneLogin");
layui.use(['form', 'jquery', "layer"], function () {
    var form = layui.form,
        $ = layui.jquery,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    //判断是否web端打开
    if (!/http(s*):\/\//.test(location.href)) {
        layer.alert("请先将项目部署到 localhost 下再进行访问【建议通过tomcat、webstorm、hb等方式运行，不建议通过iis方式运行】，否则部分数据将无法显示");
    } else {    //判断是否处于锁屏状态【如果关闭以后则未关闭浏览器之前不再显示】
        if (window.sessionStorage.getItem("lockcms") != "true" && window.sessionStorage.getItem("showNotice") != "true") {
            showNotice();
        }
    }

    //判断是否设置过头像，如果设置过则修改顶部、左侧和个人资料中的头像，否则使用默认头像
    if (window.sessionStorage.getItem('userFace') && $(".userAvatar").length > 0) {
        $("#userFace").attr("src", window.sessionStorage.getItem('userFace'));
        $(".userAvatar").attr("src", $(".userAvatar").attr("src").split("images/")[0] + "images/" + window.sessionStorage.getItem('userFace').split("images/")[1]);
    } else {
        $("#userFace").attr("src", "../../images/face.jpg");
    }

    //公告层
    function showNotice() {
        layer.open({
            type: 1,
            title: "系统公告",
            area: '800px',
            shade: 0.8,
            id: 'LAY_layuipro',
            btn: ['确认'],
            moveType: 1,
            content: '<div style="padding:15px 20px; text-align:justify; line-height: 22px; text-indent:2em;border-bottom:1px solid #e2e2e2;"><p class="layui-red">关于加强住宅小区疫情防控工作的通告。</p></pclass></p><p>为全力做好新型冠状病毒感染的肺炎疫情防控工作，有效阻断疫情传播，切实保证小区居民的生命健康安全和正常生产生活秩序，根据市新型冠状病毒感染的肺炎疫情防控应急指挥部的工作要求，现就疫情防控期间有关事项通告如下：\n' +
                '\n' +
                '一、一个大门进出。实行小区封闭式管理，对于建筑面积30万平方米以下的小区，只能开放一个大门进出（30万平方米以上的大型小区，最多开放两个大门进出）。\n' +
                '\n' +
                '二、严控外来人员。各社区应组织包括物业服务企业等有关力量，在小区出入口加强秩序维护人员，设立外来人员、返程人员登记处。对外来人员，问清楚来意，并与业主取得联系，经业主同意后方可入内，并做好记录。快递、外卖人员一律不得进入小区，由业主到大门外自行领取物件。\n' +
                '\n' +
                '三、严控外来车辆。要做好外来车辆的排查和登记工作。对外来车辆，特别是鄂牌车辆，严控进入小区，发现可疑情况及时向属地社区报告。\n' +
                '\n' +
                '四、戴口罩出入小区。进出住宅小区一律佩戴口罩。对不戴口罩进出小区的人员进行提醒劝返。同时，在指定位置设置专门的废弃口罩定点收集桶，小区业主应当主动将废弃口罩投放到定点收集桶。\n' +
                '\n' +
                '五、进入小区测量体温。在小区出入口配备测温枪，各社区应组织包括物业服务企业等有关力量，对进入小区的人员（包括车辆驾乘人员）进行体温测量。发现可疑情况立即登记、管控，并立即向属地社区上报，做好管控人员与属地社区的交接工作。\n' +
                '\n' +
                '六、必须按时消毒。在做好日常保洁工作的基础上，物业服务企业每日对电梯（内外按钮）、楼道、单元大厅、健身器材等公共区域进行消毒2次，加强通风，做好消毒记录。\n' +
                '\n' +
                '七、暂停各类工程施工。小区内业主装饰装修、小区水电煤气管道改造、路灯维修、雨污分流等工程一律暂停施工，加强管控。水电煤气、路灯等需要急修的，应报社区同意，并对施工人员测温和登记。\n' +
                '\n' +
                '八、规范台账资料。各社区应组织包括物业服务企业等有关力量，建立进出人员车辆登记簿、体温测量登记簿、小区应急处置方案、病毒消杀记录、员工安全防护措施方案、疫情防控物资储备消耗登记簿等台账资料。\n' +
                '\n' +
                '九、严格执行报告制度。各社区应组织包括物业服务企业等有关力量，履行防疫工作日报制度，对掌握的疑似人员和居家隔离人员情况、消毒次数、消毒面积、物资储备消耗情况如实向属地社区和相关部门进行报告。\n' +
                '\n' +
                '十、提高全民防控意识。多途径、全方位加强新型冠状病毒防疫知识宣传，小区宣传栏内张贴防疫宣传画，电子屏、业主微信群做好预防控制指引，发放一封信、悬挂宣传横幅，营造群防群控、科学防控和“不造谣、不传谣、不信谣”的防控氛围。\n' +
                '\n' +
                '生命重于泰山，疫情就是命令，防控就是责任！各社区应切实履行牵头抓总作用，各相关部门应做到履职尽责，各物业服务企业要充分发挥“红色物业、先锋管家”战斗堡垒作用，发动群众、依靠群众、服务群众，坚定信心、同舟共济、科学防治、精准施策，共同打赢这场疫情防控阻击战！</p></div>',
            success: function (layero) {
                var btn = layero.find('.layui-layer-btn');
                btn.css('text-align', 'center');
                btn.on("click", function () {
                    tipsShow();
                });
            },
            cancel: function (index, layero) {
                tipsShow();
            }
        });
    }

    function tipsShow() {
        window.sessionStorage.setItem("showNotice", "true");
        if ($(window).width() > 432) {  //如果页面宽度不足以显示顶部“系统公告”按钮，则不提示
            layer.tips('系统公告躲在了这里', '#userInfo', {
                tips: 3,
                time: 1000
            });
        }
    }

    $(".showNotice").on("click", function () {
        showNotice();
    })

    //锁屏
    function lockPage() {
        layer.open({
            title: false,
            type: 1,
            content: '<div class="admin-header-lock" id="lock-box">' +
                // '<div class="admin-header-lock-img"><img src="" class="userAvatar"/></div>' +
                '<div class="admin-header-lock-name" id="lockUserName">用户</div>' +
                '<div class="input_btn">' +
                '<input type="password" class="admin-header-lock-input layui-input" autocomplete="off" placeholder="请输入密码解锁.." name="lockPwd" id="lockPwd" />' +
                '<button class="layui-btn" id="unlock">解锁</button>' +
                '</div>' +
                '<p>请输入“123456”，否则不会解锁成功哦！！！</p>' +
                '</div>',
            closeBtn: 0,
            shade: 0.9,
            success: function () {
                //判断是否设置过头像，如果设置过则修改顶部、左侧和个人资料中的头像，否则使用默认头像
                if (window.sessionStorage.getItem('userFace') && $(".userAvatar").length > 0) {
                    $(".userAvatar").attr("src", $(".userAvatar").attr("src").split("images/")[0] + "images/" + window.sessionStorage.getItem('userFace').split("images/")[1]);
                }
            }
        })
        $(".admin-header-lock-input").focus();
    }

    $(".lockcms").on("click", function () {
        window.sessionStorage.setItem("lockcms", true);
        lockPage();
    })
    // 判断是否显示锁屏
    if (window.sessionStorage.getItem("lockcms") == "true") {
        lockPage();
    }
    // 解锁
    $("body").on("click", "#unlock", function () {
        if ($(this).siblings(".admin-header-lock-input").val() == '') {
            layer.msg("请输入解锁密码！");
            $(this).siblings(".admin-header-lock-input").focus();
        } else {
            if ($(this).siblings(".admin-header-lock-input").val() == "123456") {
                window.sessionStorage.setItem("lockcms", false);
                $(this).siblings(".admin-header-lock-input").val('');
                layer.closeAll("page");
            } else {
                layer.msg("密码错误，请重新输入！");
                $(this).siblings(".admin-header-lock-input").val('').focus();
            }
        }
    });
    $(document).on('keydown', function (event) {
        var event = event || window.event;
        if (event.keyCode == 13) {
            $("#unlock").click();
        }
    });

    //退出
    $(".signOut").click(function () {
        window.sessionStorage.removeItem("menu");
        menu = [];
        window.sessionStorage.removeItem("curmenu");
    })

    //功能设定
    $(".functionSetting").click(function () {
        layer.open({
            title: "功能设定",
            area: ["380px", "280px"],
            type: "1",
            content: '<div class="functionSrtting_box">' +
                '<form class="layui-form">' +
                '<div class="layui-form-item">' +
                '<label class="layui-form-label">开启Tab缓存</label>' +
                '<div class="layui-input-block">' +
                '<input type="checkbox" name="cache" lay-skin="switch" lay-text="开|关">' +
                '<div class="layui-word-aux">开启后刷新页面不关闭打开的Tab页</div>' +
                '</div>' +
                '</div>' +
                '<div class="layui-form-item">' +
                '<label class="layui-form-label">Tab切换刷新</label>' +
                '<div class="layui-input-block">' +
                '<input type="checkbox" name="changeRefresh" lay-skin="switch" lay-text="开|关">' +
                '<div class="layui-word-aux">开启后切换窗口刷新当前页面</div>' +
                '</div>' +
                '</div>' +
                '<div class="layui-form-item">' +
                '<label class="layui-form-label">单一登陆</label>' +
                '<div class="layui-input-block">' +
                '<input type="checkbox" name="oneLogin" lay-filter="multipleLogin" lay-skin="switch" lay-text="是|否">' +
                '<div class="layui-word-aux">开启后不可同时多个地方登录</div>' +
                '</div>' +
                '</div>' +
                '<div class="layui-form-item skinBtn">' +
                '<a href="javascript:;" class="layui-btn layui-btn-sm layui-btn-normal" lay-submit="" lay-filter="settingSuccess">设定完成</a>' +
                '<a href="javascript:;" class="layui-btn layui-btn-sm layui-btn-primary" lay-submit="" lay-filter="noSetting">朕再想想</a>' +
                '</div>' +
                '</form>' +
                '</div>',
            success: function (index, layero) {
                //如果之前设置过，则设置它的值
                $(".functionSrtting_box input[name=cache]").prop("checked", cacheStr == "true" ? true : false);
                $(".functionSrtting_box input[name=changeRefresh]").prop("checked", changeRefreshStr == "true" ? true : false);
                $(".functionSrtting_box input[name=oneLogin]").prop("checked", oneLoginStr == "true" ? true : false);
                //设定
                form.on("submit(settingSuccess)", function (data) {
                    window.sessionStorage.setItem("cache", data.field.cache == "on" ? "true" : "false");
                    window.sessionStorage.setItem("changeRefresh", data.field.changeRefresh == "on" ? "true" : "false");
                    window.sessionStorage.setItem("oneLogin", data.field.oneLogin == "on" ? "true" : "false");
                    window.sessionStorage.removeItem("menu");
                    window.sessionStorage.removeItem("curmenu");
                    location.reload();
                    return false;
                });
                //取消设定
                form.on("submit(noSetting)", function () {
                    layer.closeAll("page");
                });
                //单一登陆提示
                form.on('switch(multipleLogin)', function (data) {
                    layer.tips('温馨提示：此功能需要开发配合，所以没有功能演示，敬请谅解', data.othis, {tips: 1})
                });
                form.render();  //表单渲染
            }
        })
    })

    //判断是否修改过系统基本设置，去显示底部版权信息
    if (window.sessionStorage.getItem("systemParameter")) {
        systemParameter = JSON.parse(window.sessionStorage.getItem("systemParameter"));
        $(".footer p span").text(systemParameter.powerby);
    }


})