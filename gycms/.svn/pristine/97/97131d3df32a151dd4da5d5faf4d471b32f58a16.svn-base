layui
		.define(
				[ 'element', 'layer' ],
				function(exports) {

					var element = layui.element, $ = layui.jquery, layer = layui.layer;

					var obj = {
						init : function(str) {
							var clientHeight = document.body.clientHeight - 41 - 77;

							$('.erwei').click(function() {
								layer.open({
									type : 2,
									title : '推广二维码',
									shadeClose : true,
									shade : 0.8,
									area : [ '380px', '50%' ],
									content :str+"/erwei/skip" 
								});
							});

							var lockstate = layui.sessionData("lock", "state");
							if (lockstate == '0') {
								var index0 = layer
										.open({
											closeBtn : 0,
											title : '请输入解锁密码',
											content : '<input type="text" class="layui-input" id="pwd"/><span id="msg" style="color:red;text-align:center"></span>',
											area : 'auto',
											shade : [ 1, '#ebbc50' ],
											yes : function(index, layero) {
												var pwd = $('#pwd',
														layero.content).val();
												var pwd0 = layui.sessionData(
														"lock", "pwd");
												if (pwd == pwd0) {
													layui.sessionData("lock", {
														key : 'state',
														value : '1'
													});
													layer.close(index);
												} else {
													$('#msg', layero.content)
															.html('解锁失败');
												}
											}
										});
							}

							$('.jcllock')
									.click(
											function() {
												layer
														.prompt(
																{
																	formType : 1,
																	title : '请输入锁屏密码',
																	area : [
																			'360px',
																			'170px' ]
																},
																function(val,
																		index,
																		elem) {
																	layui
																			.sessionData(
																					"lock",
																					{
																						key : 'pwd',
																						value : val
																					});
																	layui
																			.sessionData(
																					"lock",
																					{
																						key : 'state',
																						value : '0'
																					});
																	layer
																			.close(index);

																	var index0 = layer
																			.open({
																				closeBtn : 0,
																				title : '请输入解锁密码',
																				content : '<input type="text" class="layui-input" id="pwd"/><span id="msg" style="color:red;text-align:center"></span>',
																				area : [
																						'360px',
																						'170px' ],
																				shade : [
																						1,
																						'#ebbc50' ],
																				yes : function(
																						index,
																						layero) {
																					var pwd = $(
																							'#pwd',
																							layero.content)
																							.val();
																					var pwd0 = layui
																							.sessionData(
																									"lock",
																									"pwd");
																					if (pwd == pwd0) {
																						layui
																								.sessionData(
																										"lock",
																										{
																											key : 'state',
																											value : '1'
																										});
																						layer
																								.close(index);
																					} else {
																						$(
																								'#msg',
																								layero.content)
																								.html(
																										'解锁失败');
																					}
																				}
																			});
																});
											});

							$(".refreshThis")
									.click(
											function() {
												var $lis = $(".layui-tab-title li");
												if ($lis.length == 0) {
													layer.msg('当前无可刷新页面');
												} else {
													$lis
															.each(function() {
																if ('layui-this' == $(
																		this)
																		.attr(
																				'class')) {
																	var compare = $(
																			this)
																			.attr(
																					'lay-id');
																	$('iframe')
																			.each(
																					function() {
																						if (compare == $(
																								this)
																								.attr(
																										'lay-id')) {

																							this.contentWindow.location
																									.reload(true);
																						}
																					});
																	return;
																}
															});
												}
											});
							$(".closePageOther")
									.click(
											function() {
												var $lis = $(".layui-tab-title li");
												if ($lis.length == 0) {
													layer.msg('当前无可关闭的页面');
												} else {
													$lis
															.each(function() {
																if ($(this)
																		.attr(
																				'class') != 'layui-this') {
																	$(this)
																			.find(
																					'i')
																			.click();
																}
															});
												}
											});
							$(".closePageAll").click(function() {
								$(".layui-tab-title li i").click();
							});

							$(".changema")
									.click(
											function() {
												layer
														.open({
															title : '修改密码',
															type : 2,
															content : str
																	+ '/login/Alert/loginPwdUpdate',
															area : [ "460px",
																	"330px" ],
															moveOut : true
														});
											});

							$(".close").click(
									function() {
										layer.confirm('确定要退出吗?', {
											btn : [ '确定', '取消' ],
											btn1 : function(index, layero) {
												location.href = str
														+ '/login/loginOut';
											}
										});
									});
							$(".oom")
									.click(
											function() {
												var layId = $(this).attr(
														"lay-id");
												var title = $(this).find("a")
														.text();
												var $lis = $(".layui-tab-title li");
												if ($lis.length == 0) {
													element
															.tabAdd(
																	'indextab',
																	{
																		title : title,
																		content : '<iframe src="'
																				+ str
																				+ "/"
																				+ layId
																				+ '/skip'
																				+ '" width="100%" height="'
																				+ clientHeight
																				+ 'px" scrolling ="yes" lay-id="'
																				+ layId
																				+ '"></iframe>',
																		id : layId
																	});
													element.tabChange(
															'indextab', layId);
												} else {
													var flag = false;
													$lis
															.each(function() {
																if ($(this)
																		.attr(
																				"lay-id") == layId) {
																	flag = true;
																}
															});
													if (flag) {
														element.tabChange(
																'indextab',
																layId);
													} else {
														element
																.tabAdd(
																		'indextab',
																		{
																			title : title,
																			content : '<iframe src="'
																					+ str
																					+ "/"
																					+ layId
																					+ '/skip'
																					+ '" width="100%" height="'
																					+ clientHeight
																					+ 'px" scrolling ="yes" lay-id="'
																					+ layId
																					+ '"></iframe>',
																			id : layId
																		});
														element.tabChange(
																'indextab',
																layId);
													}
												}
											});
						}
					};

					exports('slidTab', obj);
				});