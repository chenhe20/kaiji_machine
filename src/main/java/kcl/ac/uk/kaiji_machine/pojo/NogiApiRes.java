package kcl.ac.uk.kaiji_machine.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author He Chen
 * @university King's College London
 * @ID 21044375
 */

@Data
public class NogiApiRes {
    String count;
    List<NogiBlog> data;
}
