import jakarta.persistence.*;


import java.util.Set;

import static jakarta.persistence.FetchType.EAGER;

@Entity
@Table(name = "OT_GR_TXN_DETAILS")
@IdClass(InProcessRefSeqNoId.class)
public class InProcessGuaranteeEntity {

    private String refNo;
    private String seqNo;

    private Set<InProcessPartyEntity> parties;

    private String guaranteeNumber;

    private String entityId;

    private InProcessWorkUnitEntity workUnit;

    private String partyUnderObligor;

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

    @OneToMany(fetch = EAGER,cascade=CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "REF_NO", referencedColumnName = "REF_NO"),
            @JoinColumn(name = "SEQ_NO", referencedColumnName = "SEQ_NO")
    })
    public Set<InProcessPartyEntity> getParties() {
        return parties;
    }

    public void setParties(Set<InProcessPartyEntity> parties) {
        this.parties = parties;
    }

    @Basic
    @Column(name = "LETTR_GUAR_NO")
    public String getGuaranteeNumber() {
        return guaranteeNumber;
    }

    public void setGuaranteeNumber(String lettrGuarNo) {
        this.guaranteeNumber = lettrGuarNo;
    }

    @Basic
    @Column(name = "ENTITYID")
    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }


    @OneToOne(fetch = EAGER,cascade=CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "REF_NO", referencedColumnName = "REF_NO"),
            @JoinColumn(name = "SEQ_NO", referencedColumnName = "SEQ_NO")
    })
    public InProcessWorkUnitEntity getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(InProcessWorkUnitEntity workUnit) {
        this.workUnit = workUnit;
    }

    @Basic
    @Column(name="PARTY_UNDER_OBLIGOR")
    public String getPartyUnderObligor() {
        return partyUnderObligor;
    }

    public void setPartyUnderObligor(String partyUnderObligor) {
        this.partyUnderObligor = partyUnderObligor;
    }


}
