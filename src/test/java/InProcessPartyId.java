import jakarta.persistence.Column;


import java.io.Serializable;


public class InProcessPartyId implements Serializable {

    private String refNo;

    private String seqNo;

    private String partyCode;


    @Column(name = "REF_NO")
    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    @Column(name = "SEQ_NO")
    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }


    @Column(name = "PARTY_CODE")
    public String getPartyCode() {
        return partyCode;
    }

    public void setPartyCode(String partyCode) {
        this.partyCode = partyCode;
    }

}
