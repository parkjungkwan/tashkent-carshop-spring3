package texas.sbv.car.car;

import lombok.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Component
@Entity
@NoArgsConstructor
@Setter(AccessLevel.PUBLIC)
@Getter
@ToString
@Lazy
@Table(name = "cars")
public class Car implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull @Column(name = "cid") private Long cid;
    @NotNull @Column(name = "carcd", length = 12) private String carcd;
    @Column(name = "optioncd", length = 500) private String optioncd;
    @Column(name = "categorycd", length = 3) private String categorycd;
    @Column(name = "center_code", length = 3) private String centerCode;
    @Column(name = "middle_img", length = 100) private String middleImg;
    @Column(name = "elan_path", length = 100) private String elanPath;
    @Column(name = "exterior_colordcd", length = 10) private String exteriorColordcd;
    @Column(name = "center_region", length = 10) private String centerRegion;
    @Column(name = "ecc_reg_dtm", length = 14) private String eccRegDtm;
    @Column(name = "begin_year") private Integer beginYear;
    @Column(name = "makecd", length = 3) private String makecd;
    @Column(name = "small_img", length = 100) private String smallImg;
    @Column(name = "modelnm", length = 45) private String modelnm;
    @Column(name = "optioncd_name", length = 1500) private String optioncdName;
    @Column(name = "car_type", length = 3) private String carType;
    @Column(name = "pn_mobile", length = 16) private String pnMobile;
    @Column(name = "usernm", length = 8) private String usernm;
    @Column(name = "milage") private Integer milage;
    @Column(name = "simple_comment", length = 100) private String simpleComment;
    @Column(name = "modelnm_text", length = 100) private String modelnmText;
    @Column(name = "transmissioncd", length = 100) private String transmissioncd;
    @Column(name = "car_number", length = 9) private String carNumber;
    @Column(name = "price") private Integer price;
    @Column(name = "fule_typed_name", length = 9) private String fuleTypedName;
    @Column(name = "car_url", length = 100) private String carUrl;
    @Column(name = "fuel_typed", length = 3) private String fuelTyped;
    @Column(name = "center_region_code", length = 10) private String centerRegionCode;
    @Column(name = "truck_name", length = 100) private String truckName;
    @Column(name = "categorynm", length = 50) private String categorynm;
    @Column(name = "exterior_colornm", length = 50) private String exteriorColornm;
    @Column(name = "hot_markcd", length = 15) private String hotMarkcd;
    @Column(name = "transmissioncd_name", length = 10) private String transmissioncdName;
    @Column(name = "pass_cnt", length = 10) private String passCnt;
    @Column(name = "modelcd", length = 3) private String modelcd;
    @Column(name = "rec_comment_cd", length = 3) private String recCommentCd;
    @Column(name = "makenm", length = 20) private String makenm;
    @Column(name = "pn", length = 16) private String pn;
    @Column(name = "mfr_date", length = 60) private String mfrDate;
    @Column(name = "model_grp_cd", length = 50) private String modelGrpCd;
    @Column(name = "center_name", length = 20) private String centerName;
    @Column(name = "model_grp_nm", length = 100) private String modelGrpNm;

    @Builder
    public Car(
            String carcd, String optioncd, String categorycd, String centerCode, String middleImg, String elanPath,
            String exteriorColordcd, String centerRegion, String eccRegDtm, int beginYear, String makecd,
            String smallImg, String modelnm, String optioncdName, String carType, String pnMobile, String usernm,
            int milage, String simpleComment, String modelnmText, String transmissioncd, String carNumber,
            int price, String fuleTypedName, String carUrl, String fuelTyped, String centerRegionCode,
            String truckName, String categorynm, String exteriorColornm, String hotMarkcd, String transmissioncdName,
            String passCnt, String modelcd, String recCommentCd, String makenm, String pn, String mfrDate,
            String modelGrpCd, String centerName, String modelGrpNm
    ) {
        Assert.hasText(carcd, "carcd must not be empty");

        this.centerRegion = centerRegion;
        this.modelGrpNm = modelGrpNm;
        this.carcd = carcd;
        this.optioncd = optioncd;
        this.categorycd = categorycd;
        this.centerCode = centerCode;
        this.middleImg = middleImg;
        this.elanPath = elanPath;
        this.exteriorColordcd = exteriorColordcd;
        this.centerCode = centerCode;
        this.eccRegDtm = eccRegDtm;
        this.beginYear = beginYear;
        this.makecd = makecd;
        this.smallImg = smallImg;
        this.modelnm = modelnm;
        this.optioncdName = optioncdName;
        this.carType = carType;
        this.pnMobile = pnMobile;
        this.usernm = usernm;
        this.milage = milage;
        this.modelnmText = modelnmText;
        this.transmissioncd = transmissioncd;
        this.simpleComment = simpleComment;
        this.price = price;
        this.fuleTypedName = fuleTypedName;
        this.carUrl = carUrl;
        this.fuelTyped = fuelTyped;
        this.centerRegionCode = centerRegionCode;
        this.truckName = truckName;
        this.categorynm = categorynm;
        this.exteriorColornm = exteriorColornm;
        this.hotMarkcd = hotMarkcd;
        this.transmissioncdName = transmissioncdName;
        this.passCnt = passCnt;
        this.modelcd = modelcd;
        this.recCommentCd = recCommentCd;
        this.makenm = makenm;
        this.pn = pn;
        this.mfrDate = mfrDate;
        this.modelGrpCd = modelGrpCd;
        this.centerName = centerName;
        this.modelGrpCd = modelGrpCd;
        this.carNumber = carNumber;
    }



}
