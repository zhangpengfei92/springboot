package com.jcl.gycms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
@Controller
public class AuthController {
    /*private static final Log log = LogFactory.getLog(AuthController.class);

    @Autowired
    private UserinfoService userInfoService;

    @RequestMapping("/")
    @ResponseBody
    public String execute(HttpServletRequest request) throws Exception {

        String cmd = request.getParameter("cmd");

        boolean is_mobile = false;

        String id = request.getParameter("id");
        id = new String(id.getBytes(), "gbk");
        String password = request.getParameter("pwd");
        if (password == null) password = "";
        String online = request.getParameter("online");
        String endtime = request.getParameter("t");
        String encodetype = request.getParameter("encodetype") == null ? "0" : request.getParameter("encodetype");
        String cpu = request.getParameter("cpu") == null ? "" : request.getParameter("cpu");
        String mac = request.getParameter("mac") == null ? "" : request.getParameter("mac");
        String diskid = request.getParameter("diskid") == null ? "" : request.getParameter("diskid");
        String phone = request.getParameter("phone") == null ? "" : request.getParameter("phone");
        String opt = request.getParameter("opt") == null ? "" : request.getParameter("opt");
        String exemd5 = request.getParameter("exemd5") == null ? "" : request.getParameter("exemd5");
        String otherinfo = request.getParameter("otherinfo") == null ? "" : request.getParameter("otherinfo");
        String campaignId = request.getParameter("campaignId") == null ? "" : request.getParameter("campaignId");
        String ip = this.getIpAddr(request);
        if (endtime == null || endtime.equals("")) endtime = Function.getDateStringByPattern("yyyy-MM-dd");
        int errorType = -1;
        Userinfo user = null;
        if (StringUtils.isEmpty(id) || StringUtils.isEmpty(cmd)) {
            errorType = 0;
        } else {
            // ��½ģ��
            if (cmd.equals("login")) {

                String qstring = request.getQueryString();
                String crc = request.getParameter("crc");

                if (id != null && id.indexOf("cctvadmin") >= 0) {
                    if (ip != null && ip.indexOf("49.4.138.229") < 0) {
                        Document doc = getNoCRCDocument();
                        XMLOutputter xmlOutput = new XMLOutputter(
                                Format.getPrettyFormat());
                        xmlOutput.output(doc, out);
                        return null;
                    }
                }

                if (StringUtils.isEmpty(crc)) {
                    Document doc = this.getNoCRCDocument();
                    XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
                    xmlOutput.output(doc, out);
                    return null;
                } else {
                    // ����ж��߼����������Ѿ��е�¼��Ϣ��mac��ַһ�£��򲻽��и��online�ֶ�
                    user = CacheFactory.getUser(id);
                    if (user != null) {
                        if (mac.equals(user.getNetcard()) && password.equals(user.getPassword()))// ˵����ͬһ����������ĵ�¼���󣬲����online�ֶΣ���ֹ���Լ�
                        {
                            System.out.println("�Ѿ����ڴ�mac��ַ����������֤:::" + mac);
                            errorType = -1;// �޴�
                            Document doc = getXMLDocument(user, errorType, endtime);
                            XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
                            xmlOutput.output(doc, out);
                            return null;
                        }
                    }
                    int at1 = qstring.indexOf("crc=");
                    qstring = qstring.substring(0, at1 - 1);

                }
                user = userInfoService.getUser(id);
                if (user == null) {
                    errorType = 1;
                } else {
                    String encodepassword = this.getEncodePsss(id, user.getPassword(), encodetype);
                    if (!encodepassword.equalsIgnoreCase(password))// ��ҪMD5����
                        errorType = 2;
                        // Ȩ����֤��ʽ�Ѿ����
                    else {
                        if (user.getEnddate() != null)
                            if (user.getEnddate().before(new Date())) {
                                errorType = 3;
                            }
                    }
                }

                if (errorType == -1) {
                    String uuid = UUID.randomUUID().toString();
                    if (is_mobile) {
                        userInfoService.updateMobileOnlie(uuid, String.valueOf(user.getId()));
                    } else {
                        userInfoService.updateOnlie(uuid, String.valueOf(user.getId()));
                    }

                    user.setOnline(uuid);
                    // user.setId(Integer.parseInt(id));
                    user.setUsername(id);
                    String userRight = getUserRights(user.getUsername());
                    user.setInfo(userRight);
                    user.setNetcard(mac);
                    user.setUpdatedAt(new Date());
                    if (CacheFactory.getServerStatus() == true) {
                        CacheFactory.addUserInfo(id, user);
                    }
                    // ��¼��½��־
                    userService.addUserLogin(user.getId(), 0, cpu, mac, diskid,
                            phone, opt, exemd5, otherinfo, ip, campaignId);
                }
                Document doc = getXMLDocument(user, errorType, endtime);
                XMLOutputter xmlOutput = new XMLOutputter(
                        Format.getPrettyFormat());
                xmlOutput.output(doc, out);
                return null;
            } else if (cmd.equals("unique")) {
                if (id == null || online == null) {
                    Document doc = getCheckOnlineXMLDocument(null, -1, endtime,
                            0);
                    XMLOutputter xmlOutput = new XMLOutputter(
                            Format.getPrettyFormat());
                    xmlOutput.output(doc, out);
                    return null;
                }
                log.info("user unique:" + id + "\t" + online + "\t");
                user = CacheFactory.getUser(id, online);
                errorType = -1;
                // ����ѯ�Ļ���Ϊ�գ�������񲻿���ʱ����ʱ��ѯ��ݿ�,����ݿ⸴���û�
                // �����쳣����£�online�����ݿ�
                if (user == null) {
                    // ��Ҫ����ݿ��ѯ
                    user = userService.getUser(id);
                    if (is_mobile) {
                        user.setOnline(user.getOnline_mobile());
                    }
                    if (user != null && user.getOnline().equals(online)) {
                        errorType = 1;
                    } else {
                        log.info("user unique error:" + user.getUsername()
                                + "\t" + user.getOnline() + "\t" + online);
                    }
                    if (user != null)
                        log.info("Ŀ��û����...��ѯ��ݿ⣺" + user.getUsername() + "\t"
                                + errorType);
                    // ����cache
                    String userRight = getUserRights(user.getUsername());
                    user.setInfo(userRight);
                    if (user != null && user.getOnline().equals(online)) {
                        // ��֤�ɹ������سɹ�xml
                        if (CacheFactory.getServerStatus() == true) {
                            CacheFactory.addUserInfo(user.getUsername(), user);
                            log.info("����ָ�,��ʼ�������ڵ��û���Ϣͬ�������棺"
                                    + user.getUsername());

                        }
                    }
                }
                if (user != null && user.getOnline().equals(online)) {
                    // ��֤�ɹ������سɹ�xml
                    errorType = 1;
                }
                // ���xml����
                int needRight = 1;
                String crc = request.getParameter("crc");
                if (crc != null && !crc.equals("")) {
                    // �ж�Ч�����Ƿ�Ϊ�գ����ǵ����ж��Ƿ񱻸�ģ������µ�Ȩ��
                    *//*
                     * CRC32 crc32 = new CRC32(); byte[] data =
                     * user.getInfo().getBytes(); crc32.update(data);
                     * if(crc32.getValue()!=Integer.parseInt(crc)) { //����Ȩ���ֶ�
                     * needRight = 1; }
                     *//*
                }
                // -----------------------------------------------------------------------
                if (user != null) {
                    if (user.getUsername().indexOf("cctvadmin") >= 0) {
                        if (ip != null && ip.indexOf("49.4.138.229") >= 0) {
                            errorType = 1;
                            user.setOnline(online == null ? "" : online);
                        }
                    }
                }
                // -----------------------------------------------------------------------
                Document doc = getCheckOnlineXMLDocument(user, errorType,
                        endtime, needRight);
                XMLOutputter xmlOutput = new XMLOutputter(
                        Format.getPrettyFormat());
                xmlOutput.output(doc, out);
                return null;
            } else if (cmd.equals("logout")) {
                boolean hasDelete = CacheFactory.deleteUser(id, online);
                if (hasDelete)// �˳��ɹ�����¼�ǳ���־
                {
                    userService.addUserLogin(user.getId(), 1, cpu, mac, diskid,
                            phone, opt, exemd5, otherinfo, ip, campaignId);
                }
                // ���xml����
                Document doc = this.getLoginoutXMLDocument(hasDelete);
                XMLOutputter xmlOutput = new XMLOutputter(
                        Format.getPrettyFormat());
                xmlOutput.output(doc, out);
                return null;
            }
            // ���߲���
            else if (cmd.equals("tickout")) {
                user = CacheFactory.getUser(id);
                if (user != null) {
                    boolean hasDelete = CacheFactory.deleteUser(id, online);
                    if (hasDelete)// �˳��ɹ�����¼�ǳ���־
                    {
                        userService.addUserLogin(user.getId(), 2, cpu, mac,
                                diskid, phone, opt, exemd5, otherinfo, ip,
                                campaignId);
                    }
                    // ���xml����
                    Document doc = this.getLoginoutXMLDocument(hasDelete);
                    XMLOutputter xmlOutput = new XMLOutputter(
                            Format.getPrettyFormat());
                    xmlOutput.output(doc, out);
                }
                return null;
            } else {
                errorType = 4;
            }
        }
        Document doc = getXMLDocument(user, errorType, endtime);
        XMLOutputter xmlOutput = new XMLOutputter(Format.getPrettyFormat());
        xmlOutput.output(doc, out);
        return null;
    }

    private Document getXMLDocument(Userinfo user, int errorType, String endtime) {
        Element auth = new Element("auth");
        Document myDocument = new Document(auth);
        if (errorType > -1) {
            switch (errorType) {
                case 0:
                    auth.addContent(new Element("reason").addContent("输入用户名为空!"));
                    break;
                case 1:
                    auth.addContent(new Element("reason").addContent("用户名不存在!"));
                    break;
                case 2:
                    auth.addContent(new Element("reason").addContent("�û������벻ƥ��!"));
                    break;
                case 3:
                    auth.addContent(new Element("reason").addContent("账号已过期!"));
                    break;
                case 4:
                    auth.addContent(new Element("reason").addContent("非法请求!"));
            }
            auth.addContent(new Element("result").addContent("0"));
        } else {
            auth.addContent(new Element("name").addContent(user.getUsername()));
            auth.addContent(new Element("lvl").addContent("9"));
            auth.addContent(new Element("online").addContent(user.getOnline()));
            auth.addContent(new Element("id").addContent(String.valueOf(user.getUsername())));
            auth.addContent(new Element("result").addContent("1"));
            auth.addContent(new Element("mobile").addContent("1"));
            auth.addContent(new Element("usertype").addContent(String.valueOf(user.getUsertype())));
            auth.addContent(new Element("info").addContent(user.getInfo()));
        }
        return myDocument;
    }

    private Document getLoginoutXMLDocument(boolean hasDelete) {
        Element auth = new Element("auth");
        Document myDocument = new Document(auth);
        auth.addContent(new Element("reason").addContent("exit sucess!"));
        return myDocument;
    }

    private Document getCheckOnlineXMLDocument(Userinfo user, int errorType,
                                               String endtime, int needRight) {
        Element auth = new Element("auth");
        Document myDocument = new Document(auth);
        if (errorType != 1) {
            auth.addContent(new Element("reason").addContent("�û�������!"));
            auth.addContent(new Element("result").addContent("0"));
        } else {
            auth.addContent(new Element("name").addContent(user.getUsername()));
            auth.addContent(new Element("lvl").addContent("9"));
            auth.addContent(new Element("online").addContent(user.getOnline()));
            auth.addContent(new Element("id").addContent(String.valueOf(user.getUsername())));
            auth.addContent(new Element("check_unique").addContent("180"));
            auth.addContent(new Element("result").addContent("1"));
            auth.addContent(new Element("usertype").addContent(String.valueOf(user.getUsertype())));
            if (needRight == 1)
                auth.addContent(new Element("info").addContent(user.getInfo()));
        }

        return myDocument;
    }

    private Document getNoCRCDocument() {
        Element auth = new Element("auth");
        Document myDocument = new Document(auth);
        auth.addContent(new Element("reason").addContent("�ͻ�����Ҫ��!"));
        auth.addContent(new Element("result").addContent("0"));
        return myDocument;
    }

    private String getEncodePsss(String id, String password, String type) {
        String retPass;

        if (type.equals("1")) {
            // retPass = password;
            MD5 md5 = new MD5();
            retPass = md5.getMD5(password);
        } else if (type.equals("2")) {
            MD5 md5 = new MD5();
            retPass = md5.getMD5(password);
        } else// Ĭ�ϵ�
        {
            MD5 md5 = new MD5();
            retPass = md5.getMD5("[" + id.toLowerCase() + password
                    + id.toLowerCase() + "]");// MD5����
        }
        return retPass;
    }

    private String getUserRights(String username) {
        List list = userInfoService.getUserRights(username);
        Object[] obj;
        StringBuffer sb = new StringBuffer();
        for (Iterator<Object[]> it = list.iterator(); it.hasNext(); ) {
            obj = it.next();
            sb.append(obj[1] + "," + obj[2].toString().substring(0, 10) + ","
                    + obj[3].toString().substring(0, 10) + "|");
        }
        if (sb.length() > 0)
            sb = sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }*/
}
