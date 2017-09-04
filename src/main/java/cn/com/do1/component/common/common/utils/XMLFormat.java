package cn.com.do1.component.common.common.utils;

/**
 * 
 */
public class XMLFormat {
    private boolean newlines;

    private String encoding;

    private boolean textNormalize;

    private boolean expandEmptyElements;

    private boolean omitDeclaration;

    private boolean omitEncoding;

    public XMLFormat() {
        this.newlines = false;
        this.encoding = "utf-8";
        this.textNormalize = false;
        this.expandEmptyElements = false;
        this.omitDeclaration = false;
        this.omitEncoding = false;
    }

    public boolean isNewlines() {
        return newlines;
    }

    public void setNewlines(boolean newlines) {
        this.newlines = newlines;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public boolean isTextNormalize() {
        return textNormalize;
    }

    public void setTextNormalize(boolean textNormalize) {
        this.textNormalize = textNormalize;
    }

    public boolean isExpandEmptyElements() {
        return expandEmptyElements;
    }

    public void setExpandEmptyElements(boolean expandEmptyElements) {
        this.expandEmptyElements = expandEmptyElements;
    }

    public boolean isOmitDeclaration() {
        return omitDeclaration;
    }

    public void setOmitDeclaration(boolean omitDeclaration) {
        this.omitDeclaration = omitDeclaration;
    }

    public boolean isOmitEncoding() {
        return omitEncoding;
    }

    public void setOmitEncoding(boolean omitEncoding) {
        this.omitEncoding = omitEncoding;
    }
}

