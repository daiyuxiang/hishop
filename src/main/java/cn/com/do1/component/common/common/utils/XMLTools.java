package cn.com.do1.component.common.common.utils;


import org.apache.commons.lang3.StringUtils;
import org.jdom.*;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * To change this template use Options | File Templates.
 */
public class XMLTools {
    private static SAXBuilder builder = new SAXBuilder(false);
    private Document doc;
    private Element rootElement;
    private String fileName;
    private Map nsMap = new HashMap();
    private XMLFormat outFormatObj = new XMLFormat();

    public XMLTools() throws Exception {

    }

    public void parseNamespace() {
        List list = rootElement.getAdditionalNamespaces();
        for (int i = 0; i < list.size(); i++) {
            Namespace n = (Namespace) list.get(i);
            nsMap.put(n.getPrefix(), n);
        }
    }

    public XMLTools(URL _url) throws Exception {
        System.out.println("_url=" + _url.getFile());
        System.out.println("_url=" + _url.getPath());
        doc = builder.build(_url);
        rootElement = doc.getRootElement();
        parseNamespace();
        fileName = _url.getFile();
    }

    public XMLTools(Reader _reader) throws Exception {
        doc = builder.build(_reader);
        rootElement = doc.getRootElement();
        parseNamespace();
    }

    public XMLTools(InputStream _reader) throws Exception {
        doc = builder.build(_reader);
        rootElement = doc.getRootElement();
        parseNamespace();

    }

    public XMLTools(String _file) throws Exception {
        fileName = _file;
        doc = builder.build(new File(_file));
        rootElement = doc.getRootElement();
        parseNamespace();
    }

    public XMLTools(File _file) throws Exception {
        fileName = _file.getPath();
        doc = builder.build(_file);
        rootElement = doc.getRootElement();
        parseNamespace();
    }

    /**
     * 传入一个文件路径后，解析文件
     *
     * @param _xmlFileName
     * @throws org.jdom.JDOMException
     * @throws java.io.IOException
     */

    public void parseXML(String _xmlFileName) throws JDOMException, IOException {
        doc = builder.build(_xmlFileName);
        rootElement = doc.getRootElement();
        parseNamespace();
    }

//    public XMLTools parseEjbJar(String _file) throws Exception {
//        doc = builder.build(new JarBuilder(_file).getFile());
//        rootElement = doc.getRootElement();
//        parseNamespace();
//        return this;
//    }

    public Element getRootElement() {
        return rootElement;
    }

    /**
     * 取得xml中所有的元素名称
     * */

    /**
     * 取得属性的值,元素级别用.表示,如aido.config.ejb
     */
    public String[] getPropertie(String _propertie) throws Exception {
        return getPropertieByParent(rootElement, _propertie);
    }

    public String getSinglePropertie(String _propertie) throws Exception {
        return getSinglePropertieByParent(rootElement, _propertie);
    }


    /**
     * 取得属性的值,元素级别用.表示如aido.config.ejb
     * 如果要取config下ejb的值则参数如下aido.config,ejb
     */
    public String[] getPropertieByParent(Element _parent, String _propertie) throws Exception {
        Element list[] = getElementsByParent(_parent, _propertie);
        if (list != null && list.length > 0) {
            String rs[] = new String[list.length];
            for (int j = 0; j < list.length; j++) {
                rs[j] = list[j].getText();
            }
            return rs;
        } else
            return null;
    }

    public String getSinglePropertieByParent(Element _parent, String _propertie) throws Exception {
        Element list = getSingleElementsByParent(_parent, _propertie);
        if (list != null) {
            return list.getText();
        } else
            return null;
    }

    /**
     * 根据父结点名和父结点值。取得属性的值,元素级别用.表示如aido.config.ejb
     * 如果要取config下ejb的值则参数如下aido.config,值,ejb
     * caution: 适用于父结点只有一个确切的值元素(2005.1.1日起不再有此限制)
     */
    public String[] getPropertieByParentValue(String _parent, String _parentValue, String _propertie) throws Exception {
        Element element[] = getElementByNameAndValue(_parent, _parentValue);
        ArrayList allList = new ArrayList();
        if (element != null && element.length > 0)
            for (int i = 0; i < element.length; i++) {
                Element temp[] = getElementsByParent(element[i], _propertie);
                if (temp != null && temp.length > 0)
                    allList.add(temp);
            }
        if (allList != null) {
            String rs[] = new String[allList.size()];
            for (int j = 0; j < allList.size(); j++) {
                rs[j] = ((Element) allList.get(j)).getText();
            }
            return rs;
        } else
            return null;
    }

    /**
     * 根据命名空间名得到对象
     *
     * @param _perfix
     * @return
     */
    public Namespace getNamespace(String _perfix) {
        Object obj = nsMap.get(_perfix);
        if (obj == null) return null;
        return (Namespace) obj;
    }

    /**
     * 根据路径名得到结点
     *
     * @param _propertie
     * @return
     * @throws Exception
     */
    public Element[] getElements(String _propertie) throws Exception {
        return getElementsByParent(rootElement, _propertie);
    }

    public Element[] getElements(String _propertie, Namespace _nameSpace) throws Exception {
        return getElementsByParent(rootElement, _propertie, _nameSpace);
    }

    /**
     * 根据父结点和子结点名及子结点自身属性得到子结点
     * para:parent 父结点
     * para:attribName 父结点属性名
     * para:attribValue 父结点属性值
     * para:propertie 子结点名
     */
    public Element[] getElementsByParentAndAttrib(Element _parent, String _attribName, String _attribValue, String _propertie) throws Exception {
        Element[] list = getElementsByParent(_parent, _propertie);
        if (list != null && list.length > 0) {
            ArrayList tempList = new ArrayList();
            for (int j = 0; j < list.length; j++) {
                if (_attribValue.equals(list[j].getAttributeValue(_attribName)))
                    tempList.add(list[j]);
            }
            if (tempList != null) {
                Element[] rel = new Element[tempList.size()];
                tempList.toArray(rel);
                return rel;
            }
        }
        return null;
    }

    /**
     * 根据父结点和子结点名得到子结点
     * para:parent 父结点
     * para:propertie 子结点名
     */
    public Element[] getElementsByParent(Element _parent, String _propertie) throws Exception {
        if (AssertUtil.isEmpty(_propertie)) throw new Exception("未将属性名传入!");
        _propertie = StringUtils.replace(_propertie, "\\.", "{point_tag_yang}");
        String[] path = _propertie.split(".");
        List list = null;
        for (int i = 0; i < path.length; i++) {
            path[i] = StringUtils.replace(path[i], "{point_tag_yang}", ".");
            String[] spath = path[i].split(":");
            if (spath.length > 1) {
                if (i == 0) {
                    list = _parent.getChildren(spath[1], (Namespace) nsMap.get(spath[0]));
                } else {
                    list = getChildrens(list, spath[1], (Namespace) nsMap.get(spath[0]));
                }
            } else {
                if (i == 0) {
                    list = _parent.getChildren(path[i]);
                } else {
                    list = getChildrens(list, path[i]);
                }
            }
        }
        if (list != null) {
            Element[] rel = new Element[list.size()];
            list.toArray(rel);
            return rel;
        }
        return null;
    }

    public Element getSingleElementsByParent(Element _parent, String _propertie) throws Exception {
        if (AssertUtil.isEmpty(_propertie)) throw new Exception("未将属性名传入!");
        String[] path = _propertie.split(",");
        List list = null;
        for (int i = 0; i < path.length; i++) {
            String[] spath = path[i].split(":");
            if (spath.length > 1) {
                if (i == 0) {
                    list = _parent.getChildren(spath[1], (Namespace) nsMap.get(spath[0]));
                } else {
                    list = getChildrens(list, spath[1], (Namespace) nsMap.get(spath[0]));
                }
            } else {
                if (i == 0) {
                    list = _parent.getChildren(path[i]);
                } else {
                    list = getChildrens(list, path[i]);
                }
            }
        }
        if (list != null && list.size() > 0) {
            return (Element) list.get(0);
        }
        return null;
    }

    public Element[] getElementsByParent(Element _parent, String _propertie, Namespace _nameSpace) throws Exception {
        if (AssertUtil.isEmpty(_propertie)) throw new Exception("未将属性名传入!");

        String[] path = _propertie.split(".");
        List list = null;
        for (int i = 0; i < path.length; i++) {
            if (i == 0) {
                list = _parent.getChildren(path[i], _nameSpace);
            } else {
                list = getChildrens(list, path[i], _nameSpace);
            }
        }
        if (list != null) {
            Element[] rel = new Element[list.size()];
            list.toArray(rel);
            return rel;
        }
        return null;
    }

    /**
     * 根据父路径及自身值得到结点
     *
     * @param _parent
     * @param _selfValue
     * @param _propertie
     * @return
     * @throws Exception
     */
    public Element[] getElementsByParentAndSelfValue(Element _parent, String _selfValue, String _propertie) throws Exception {
        Element list[] = getElementsByParent(_parent, _propertie);
        ArrayList rs = new ArrayList();
        if (list != null && list.length > 0)
            for (int i = 0; i < list.length; i++) {
                if (list[i].getText().equals(_selfValue)) rs.add(list[i]);
            }
        if (rs != null && rs.size() > 0) {
            Element temp[] = new Element[rs.size()];
            return (Element[]) rs.toArray(temp);
        } else
            return null;
    }

    /**
     * 得到一批结点的子结点
     */
    public List getChildrens(List _list, String _child) {
        ArrayList rs = new ArrayList();
        if (_list != null)
            for (int i = 0; i < _list.size(); i++) {
                List tempList = ((Element) _list.get(i)).getChildren(_child);
                if (tempList != null) rs.addAll(tempList);
            }
        return rs;
    }

    public List getChildrens(List _list, String _child, Namespace _nameSpace) {
        ArrayList rs = new ArrayList();
        if (_list != null)
            for (int i = 0; i < _list.size(); i++) {
                List tempList = ((Element) _list.get(i)).getChildren(_child, _nameSpace);
                if (tempList != null) rs.addAll(tempList);
            }
        return rs;
    }

    /**
     * 增加新的元素，如果父路径不唯一的话，则为每个父路径增加新的无素.
     *
     * @param _parent
     * @param _elementName
     * @return
     * @throws Exception
     */
    public Element[] addElement(String _parent, String _elementName) throws Exception {
        Element[] parentEle = getElements(_parent);
        Element[] re = null;
        if (parentEle != null && parentEle.length > 0) {
            re = new Element[parentEle.length];
            for (int i = 0; i < parentEle.length; i++) {
                re[i] = addElement(parentEle[i], _elementName);
            }
        }
        return re;
    }

    /**
     * 向文本中增加一个元素及其值
     *
     * @param _parent      父节点路径
     * @param _elementName 节点名称
     * @param _value       元素的值
     */
    public Element[] addElement(String _parent, String _elementName, String _value) throws Exception {
        Element el[] = addElement(_parent, _elementName);
        if (el != null && el.length > 0)
            for (int i = 0; i < el.length; i++)
                el[i].setText(_value);
        return el;
    }

    /**
     * 向文本中增加一个元素及其值
     *
     * @param _element     父节点
     * @param _elementName 节点名称
     * @return
     * @throws Exception
     */
    public Element addElement(Element _element, String _elementName) throws Exception {
        if (AssertUtil.isEmpty(_elementName)) throw new Exception("错误，新增加的元素没有名称");
        String[] path = _elementName.split(".");
        Element ele = _element;
        for (int i = 0; i < path.length; i++) {
            Element temp = new Element(path[i]);
            ele.addContent(temp);
            ele = temp;
        }
        return ele;
    }


    /**
     * 向文本中增加一个元素及其值
     *
     * @param _element     父节点路径
     * @param _elementName 节点名称
     * @param _value       元素的值
     */
    public Element addElement(Element _element, String _elementName, String _value) throws Exception {
        Element ele = addElement(_element, _elementName);
        ele.setText(_value);
        return ele;
    }

    /**
     * 向文本中增加一个元素及其属性值
     *
     * @param _element     父节点路径
     * @param _elementName 节点名称
     * @param _attrValue       元素的值
     */
    public Element addElement(Element _element, String _elementName, String _attrName, String _attrValue) throws Exception {
        Element ele = addElement(_element, _elementName);
        ele.setAttribute(_attrName, _attrValue);
        return ele;
    }

    /**
     * 向文本中删除一个元素及其值
     *
     * @param _element     父节点
     * @param _elementName 节点名称
     */
    public void removeElement(Element _element, String _elementName) throws Exception {
        Element ele[] = this.getElementsByParent(_element, _elementName);
        if (ele != null && ele.length > 0) {
            for (int i = 0; i < ele.length; i++) {
                ele[i].getParent().removeContent(ele[i]);
            }
        }
    }

    public Document getDoc() {
        return doc;
    }

    /**
     * 将文档保存
     *
     * @param _path 文档要保存的路径
     * @throws Exception
     */
    public void save(String _path) throws Exception {
        FileOutputStream writer = null;
        try {
            Format formater = Format.getPrettyFormat();
            formater.setIndent("  ");
            formater.setIgnoreTrAXEscapingPIs(true);
            formater.setEncoding(outFormatObj.getEncoding());
            writer = new FileOutputStream(_path);
            formater.setExpandEmptyElements(outFormatObj.isExpandEmptyElements());
            formater.setOmitDeclaration(outFormatObj.isOmitDeclaration());
            formater.setOmitEncoding(outFormatObj.isOmitEncoding());
            XMLOutputter outputter = new XMLOutputter(formater);
            outputter.output(doc, writer);
            writer.close();
        } catch (Exception e) {
            if (writer != null) writer.close();
            throw e;
        }
    }


    public void save() throws Exception {
        save(fileName);
    }

    /**
     * 根据XPATH来查找结点
     *
     * @param _path
     * @return
     * @throws org.jdom.JDOMException
     */
    public List getElementsByXPath(String _path) throws JDOMException {
        XPath xPathPaser = XPath.newInstance(_path);
        return xPathPaser.selectNodes(rootElement);
    }

    /**
     * 根据XPATH来查找结点
     *
     * @param _path
     * @return
     * @throws org.jdom.JDOMException
     */
    public Element getElementByXPath(String _path) throws JDOMException {
        XPath xPathPaser = XPath.newInstance(_path);
        Object obj = xPathPaser.selectSingleNode(rootElement);
        return obj == null ? null : (Element) obj;
    }

    /**
     * 根据元素路径和元素值得到元素
     *
     * @param _path  元素的完整路径名
     * @param _value 元素的值
     */
    public Element[] getElementByNameAndValue(String _path, String _value) throws Exception {
        Element[] list = getElements(_path);
        ArrayList rl = new ArrayList();
        if (list != null && list.length > 0) {
            for (int i = 0; i < list.length; i++) {
                if (list[i].getText().equals(_value)) rl.add(list[i]);
            }
        }
        Element[] el = new Element[rl.size()];
        rl.toArray(el);
        return el;
    }

    /**
     * 根据元素路径和属性值得到元素
     *
     * @param _path        元素的完整路径名
     * @param _attribName  属性名
     * @param _attribValue 属性值
     */
    public Element[] getElementByNameAndAttribValue(String _path, String _attribName, String _attribValue) throws Exception {
        Element[] list = getElements(_path);
        ArrayList rl = new ArrayList();
        if (list != null && list.length > 0) {
            for (int i = 0; i < list.length; i++) {
                if (_attribValue.equals(list[i].getAttributeValue(_attribName))) rl.add(list[i]);
            }
        }
        Element[] el = new Element[rl.size()];
        rl.toArray(el);
        return el;
    }

    /**
     * 根据路径名和属性名得到属性值
     *
     * @param _path       元素的完整路径名
     * @param _attribName 属性名
     * @return 属性值
     * @throws Exception
     */
    public String[] getElementAttrib(String _path, String _attribName) throws Exception {
        Element el[] = getElements(_path);
        String rs[] = null;
        if (el != null && el.length > 0) {
            rs = new String[el.length];
            for (int i = 0; i < el.length; i++) {
                rs[i] = el[i].getAttributeValue(_attribName);
            }
        }
        return rs;
    }


    /**
     * 为元素增加属性
     */
    public void addAttribute(Element _element, String _attName, String _attValue) throws Exception {
        _element.setAttribute(_attName, _attValue);
    }

    /**
     * 为元素设置值，如果元素不存在则新建立此元素
     *
     * @param _elementName
     * @param _value
     * @caution 此元素父路径只能唯一
     */
    public void setValue(Element _parentElement, String _elementName, String _value) throws Exception {
        Element[] element = getElementsByParent(_parentElement, _elementName);
        if (element == null || element.length < 1) {
            addElement(_parentElement, _elementName, _value);
        } else {
            for (int i = 0; i < element.length; i++)
                element[i].setText(_value);
        }
    }

    /**
     * 为元素设置属性，如果元素不存在则新建立此元素
     *
     * @param _parentElement
     * @param _elementName
     * @param _atrName
     * @param _atrValue
     */
    public void setAttrib(Element _parentElement, String _elementName, String _atrName, String _atrValue) throws Exception {
        Element[] element = getElementsByParent(_parentElement, _elementName);
        if (element == null || element.length < 1) {
            addElement(_parentElement, _elementName).setAttribute(_atrName, _atrValue);
        } else {
            for (int i = 0; i < element.length; i++)
                element[i].setAttribute(_atrName, _atrValue);
        }
    }

    /**
     * 非页面支付注入VO
     * @param _propertie
     * @return
     * @throws Exception
     */
    public String getSinglePropertieFromXml(String _propertie) throws Exception {
        Element list = getSingleElementsByParent(rootElement, _propertie);
        if (list != null) {
            return list.getText();
        }else{
        	Element datas = rootElement.getChild("datas");
        	Element list2 = getSingleElementsByParent(datas, _propertie);
        	if(list2 != null){
        		return list2.getText();
        	}else
        		return null;
        }
    }
    /**
     * 为文档设置类型
     *
     * @param _type
     */
    public void setDocType(DocType _type) {
        doc.setDocType(_type);
    }

    public DocType getDocType() {
        return doc.getDocType();
    }

    public void addContent(ProcessingInstruction _proc) {
        doc.addContent(_proc);
    }

    public ProcessingInstruction getProcess() {
        List list = doc.getContent();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getClass().isInstance(ProcessingInstruction.class))
                    return (ProcessingInstruction) list.get(i);
            }
        }
        return null;
    }

//    public void setProcess(String _targ, String _name, String _value) {
//        List list =  doc.//doc.getProcessingInstructions();
//        if (list != null && list.size() > 0) {
//            for (int i = 0; i < list.size(); i++) {
//                ProcessingInstruction pro = (ProcessingInstruction) list.get(i);
//                if (pro.getTarget().equals(_targ)) {
//                    pro.setValue(_name, _value);
//                }
//            }
//        }
//    }

    public void setProcess(ProcessingInstruction _pro) {
        List list = doc.getContent();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getClass().isInstance(ProcessingInstruction.class)) {
                    ProcessingInstruction pro = (ProcessingInstruction) list.get(i);
                    if (pro.getTarget().equals(_pro.getTarget()))
                        doc.removeContent((ProcessingInstruction) list.get(i));
//          else tempList.add(list.get(i));
//          doc.removeContent((ProcessingInstruction) list.get(i));
                }

            }
        }
        doc.addContent(_pro);
    }

    public String toString() {
        XMLOutputter outer = new XMLOutputter();
        return outer.outputString(this.getDoc());
    }

    public void setNewlines(boolean newlines) {
        outFormatObj.setNewlines(newlines);
    }

    public void setEncoding(String encoding) {
        outFormatObj.setEncoding(encoding);
    }

    public void setTextNormalize(boolean textNormalize) {
        outFormatObj.setTextNormalize(textNormalize);
    }

    public void setExpandEmptyElements(boolean expandEmptyElements) {
        outFormatObj.setExpandEmptyElements(expandEmptyElements);
    }

    public void setOmitDeclaration(boolean omitDeclaration) {
        outFormatObj.setOmitDeclaration(omitDeclaration);
    }

    public void setOmitEncoding(boolean omitEncoding) {
        outFormatObj.setOmitEncoding(omitEncoding);
    }
}
