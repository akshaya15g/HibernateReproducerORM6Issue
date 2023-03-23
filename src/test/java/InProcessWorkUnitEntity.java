import jakarta.persistence.*;


@Entity
@Table(name = "OT_WORKUNIT")
@IdClass(InProcessRefSeqNoId.class)
public class InProcessWorkUnitEntity {
    private String refNo;
    private String seqNo;
    private String regBy;

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

    @Basic
    @Column(name = "REG_BY")
    public String getRegBy() {
        return regBy;
    }

    public void setRegBy(String regBy) {
        this.regBy = regBy;
    }
}
