package vivien.doingthigns.fantasyworldgenerator.data;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Heritage implements Serializable {
    private String name;
    private int lh;
    private String[] skinColor;
    private String[] undertones;
    private String[] hairColor;
    private String[] hairstyles;
    private String[] eyeColor;
    private String[][] optionalSpecialTraits;
    private String[][] certainSpecialTraits;
}