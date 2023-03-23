import jakarta.persistence.*;


import static jakarta.persistence.FetchType.EAGER;

@Entity
@Table(name = "OT_TXN_PARTY")
@IdClass(InProcessPartyId.class)
public class InProcessPartyEntity  {


    private String refNo;
    private String seqNo;

    private String partyCode;

    private String partyId;

    private InProcessGuaranteeEntity guarantee;

    @Id
    @Column(name = "REF_NO")
    public String getRefNo() {
        return refNo;
    }


    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    @Id
    @Column(name = "SEQ_NO")
    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    @Id
    @Column(name = "PARTY_CODE")
    public String getPartyCode() {
        return partyCode;
    }

    public void setPartyCode(String partyCode) {
        this.partyCode = partyCode;
    }

    @Basic
    @Column(name = "PARTY_ID")
    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }





}
