package feihongtaxue;

import lombok.AllArgsConstructor;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;



@AllArgsConstructor
public class FlowBean implements Writable {
    private long upflow;
    private long downflow;
    private long sumflow;

    public FlowBean(){

    }

    public long getUpflow() {
        return upflow;
    }

    public void setUpflow(long upflow) {
        this.upflow = upflow;
    }

    public long getDownflow() {
        return downflow;
    }

    public void setDownflow(long downflow) {
        this.downflow = downflow;
    }

    public long getSumflow() {
        return sumflow;
    }

    public void setSumflow(long sumflow) {
        this.sumflow = sumflow;
    }



    public FlowBean(long upflow,long downflow){
        this.upflow = upflow;
        this.downflow = downflow;
        this.sumflow = upflow + downflow;
    }

    public void write(DataOutput output) throws IOException {
        output.writeLong(this.upflow);
        output.writeLong(this.downflow);
        output.writeLong(this.sumflow);
    }

    public void readFields(DataInput Input) throws IOException {
        this.upflow = Input.readLong();
        this.downflow = Input.readLong();
        this.sumflow = Input.readLong();
    }

    @Override
    public String toString() {
        return this.upflow + "\t" + this.downflow + "\t" + this.sumflow;
    }


}