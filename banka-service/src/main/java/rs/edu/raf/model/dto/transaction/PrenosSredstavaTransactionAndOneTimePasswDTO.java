package rs.edu.raf.model.dto.transaction;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrenosSredstavaTransactionAndOneTimePasswDTO {

    private PrenosSredstavaDTO prenosSredstavaDTO;
    private String oneTimePasswToken;

}
