package texas.sbv.car.record;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import texas.sbv.car.carbook.CarBook;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Component
@Entity
@Getter
@ToString
@Setter
@NoArgsConstructor
@Lazy
@Table(name="records")
public class Record  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull @Column(name = "record_id") private Long recordId;
    @Column(name = "record_date") private String recordDate;
    @Column(name = "service_code") private String serviceCode;
    @Column(name = "detail") private String detail;
    @Column(name = "price") private String price;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mycarId")
    private CarBook mycarId;



    @Builder
    public Record(String recordDate, String serviceCode, String detail,
                String price) {
        this.recordDate = recordDate;
        this.serviceCode = serviceCode;
        this.detail = detail;
        this.price = price;

    }
}
