//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.bson;

import com.mongodb.DBRefBase;
import org.bson.io.BasicOutputBuffer;
import org.bson.io.OutputBuffer;
import org.bson.types.*;

import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;

public class BasicBSONEncoder implements BSONEncoder {
    static final boolean DEBUG = false;
    /** @deprecated */
    @Deprecated
    protected OutputBuffer _buf;

    public BasicBSONEncoder() {
    }

    public byte[] encode(BSONObject o) {
        BasicOutputBuffer buf = new BasicOutputBuffer();
        this.set(buf);
        this.putObject(o);
        this.done();
        return buf.toByteArray();
    }

    public void set(OutputBuffer out) {
        if(this._buf != null) {
            throw new IllegalStateException("in the middle of something");
        } else {
            this._buf = out;
        }
    }

    protected OutputBuffer getOutputBuffer() {
        return this._buf;
    }

    public void done() {
        this._buf = null;
    }

    /** @deprecated */
    @Deprecated
    protected boolean handleSpecialObjects(String name, BSONObject o) {
        return false;
    }

    protected boolean putSpecial(String name, Object o) {
        return false;
    }

    public int putObject(BSONObject o) {
        return this.putObject((String)null, o);
    }

    protected int putObject(String name, BSONObject o) {
        if(o == null) {
            throw new NullPointerException("can\'t save a null object");
        } else {
            int start = this._buf.getPosition();
            byte myType = 3;
            if(o instanceof List) {
                myType = 4;
            }

            if(this.handleSpecialObjects(name, o)) {
                return this._buf.getPosition() - start;
            } else {
                if(name != null) {
                    this._put(myType, name);
                }

                int sizePos = this._buf.getPosition();
                this._buf.writeInt(0);
                List transientFields = null;
                boolean rewriteID = myType == 3 && name == null;
                if(myType == 3) {
                    if(rewriteID && o.containsField("_id")) {
                        this._putObjectField("_id", o.get("_id"));
                    }

                    Object temp = o.get("_transientFields");
                    if(temp instanceof List) {
                        transientFields = (List)temp;
                    }
                }

                Iterator temp1;
                if(o instanceof Map) {
                    temp1 = ((Map)o).entrySet().iterator();

                    label82:
                    while(true) {
                        Entry s;
                        do {
                            do {
                                if(!temp1.hasNext()) {
                                    break label82;
                                }

                                s = (Entry)temp1.next();
                            } while(rewriteID && ((String)s.getKey()).equals("_id"));
                        } while(transientFields != null && transientFields.contains(s.getKey()));

                        this._putObjectField((String)s.getKey(), s.getValue());
                    }
                } else {
                    temp1 = o.keySet().iterator();

                    label65:
                    while(true) {
                        String s1;
                        do {
                            do {
                                if(!temp1.hasNext()) {
                                    break label65;
                                }

                                s1 = (String)temp1.next();
                            } while(rewriteID && s1.equals("_id"));
                        } while(transientFields != null && transientFields.contains(s1));

                        Object val = o.get(s1);
                        this._putObjectField(s1, val);
                    }
                }

                this._buf.write(0);
                this._buf.writeInt(sizePos, this._buf.getPosition() - sizePos);
                return this._buf.getPosition() - start;
            }
        }
    }

    protected void _putObjectField(String name, Object val) {
        if(!name.equals("_transientFields")) {
            if(name.contains("\u0000")) {
                throw new IllegalArgumentException("Document field names can\'t have a NULL character. (Bad Key: \'" + name + "\')");
            } else if(name.equals("$where") && val instanceof String) {
                this._put((byte) 13, name);
                this._putValueString(val.toString());
            } else {
                val = BSON.applyEncodingHooks(val);
                if(val == null || "null".equals(val)) {
                    this.putNull(name);
                } else if(val instanceof Date) {
                    this.putDate(name, (Date)val);
                } else if(val instanceof Number) {
                    this.putNumber(name, (Number)val);
                } else if(val instanceof Character) {
                    this.putString(name, val.toString());
                } else if(val instanceof String) {
                    this.putString(name, val.toString());
                } else if(val instanceof ObjectId) {
                    this.putObjectId(name, (ObjectId)val);
                } else if(val instanceof BSONObject) {
                    this.putObject(name, (BSONObject)val);
                } else if(val instanceof Boolean) {
                    this.putBoolean(name, (Boolean)val);
                } else if(val instanceof Pattern) {
                    this.putPattern(name, (Pattern)val);
                } else if(val instanceof Map) {
                    this.putMap(name, (Map)val);
                } else if(val instanceof Iterable) {
                    this.putIterable(name, (Iterable)val);
                } else if(val instanceof byte[]) {
                    this.putBinary(name, (byte[])((byte[])val));
                } else if(val instanceof Binary) {
                    this.putBinary(name, (Binary)val);
                } else if(val instanceof UUID) {
                    this.putUUID(name, (UUID)val);
                } else if(val.getClass().isArray()) {
                    this.putArray(name, val);
                } else if(val instanceof Symbol) {
                    this.putSymbol(name, (Symbol)val);
                } else if(val instanceof BSONTimestamp) {
                    this.putTimestamp(name, (BSONTimestamp)val);
                } else if(val instanceof CodeWScope) {
                    this.putCodeWScope(name, (CodeWScope)val);
                } else if(val instanceof Code) {
                    this.putCode(name, (Code)val);
                } else if(val instanceof DBRefBase) {
                    BasicBSONObject temp = new BasicBSONObject();
                    temp.put("$ref", ((DBRefBase)val).getRef());
                    temp.put("$id", ((DBRefBase)val).getId());
                    this.putObject(name, temp);
                } else if(val instanceof MinKey) {
                    this.putMinKey(name);
                } else if(val instanceof MaxKey) {
                    this.putMaxKey(name);
                } else if(!this.putSpecial(name, val)) {
//                    throw new IllegalArgumentException("can\'t serialize " + val.getClass());
                    this.putNull(name);
                }

            }
        }
    }

    private void putArray(String name, Object array) {
        this._put(4, name);
        int sizePos = this._buf.getPosition();
        this._buf.writeInt(0);
        int size = Array.getLength(array);

        for(int i = 0; i < size; ++i) {
            this._putObjectField(String.valueOf(i), Array.get(array, i));
        }

        this._buf.write(0);
        this._buf.writeInt(sizePos, this._buf.getPosition() - sizePos);
    }

    private void putIterable(String name, Iterable l) {
        this._put(4, name);
        int sizePos = this._buf.getPosition();
        this._buf.writeInt(0);
        int i = 0;

        for(Iterator var5 = l.iterator(); var5.hasNext(); ++i) {
            Object obj = var5.next();
            this._putObjectField(String.valueOf(i), obj);
        }

        this._buf.write(0);
        this._buf.writeInt(sizePos, this._buf.getPosition() - sizePos);
    }

    private void putMap(String name, Map m) {
        this._put(3, name);
        int sizePos = this._buf.getPosition();
        this._buf.writeInt(0);
        Iterator var4 = m.entrySet().iterator();

        while(var4.hasNext()) {
            Entry entry = (Entry)var4.next();
            this._putObjectField(entry.getKey().toString(), entry.getValue());
        }

        this._buf.write(0);
        this._buf.writeInt(sizePos, this._buf.getPosition() - sizePos);
    }

    protected void putNull(String name) {
        this._put(10, name);
    }

    protected void putUndefined(String name) {
        this._put(6, name);
    }

    protected void putTimestamp(String name, BSONTimestamp ts) {
        this._put(17, name);
        this._buf.writeInt(ts.getInc());
        this._buf.writeInt(ts.getTime());
    }

    protected void putCodeWScope(String name, CodeWScope code) {
        this._put(15, name);
        int temp = this._buf.getPosition();
        this._buf.writeInt(0);
        this._putValueString(code.getCode());
        this.putObject(code.getScope());
        this._buf.writeInt(temp, this._buf.getPosition() - temp);
    }

    protected void putCode(String name, Code code) {
        this._put(13, name);
        int temp = this._buf.getPosition();
        this._putValueString(code.getCode());
    }

    protected void putBoolean(String name, Boolean b) {
        this._put(8, name);
        this._buf.write(b.booleanValue()?1:0);
    }

    protected void putDate(String name, Date date) {
        this._put(9, name);
        this._buf.writeLong(date.getTime());
    }

    protected void putNumber(String name, Number n) {
        if(!(n instanceof Integer) && !(n instanceof Short) && !(n instanceof Byte) && !(n instanceof AtomicInteger)) {
            if(!(n instanceof Long) && !(n instanceof AtomicLong)) {
                if(!(n instanceof Float) && !(n instanceof Double)) {
                    throw new IllegalArgumentException("can\'t serialize " + n.getClass());
                }

                this._put(1, name);
                this._buf.writeDouble(n.doubleValue());
            } else {
                this._put(18, name);
                this._buf.writeLong(n.longValue());
            }
        } else {
            this._put(16, name);
            this._buf.writeInt(n.intValue());
        }

    }

    protected void putBinary(String name, byte[] data) {
        this.putBinary(name, 0, data);
    }

    protected void putBinary(String name, Binary val) {
        this.putBinary(name, val.getType(), val.getData());
    }

    private void putBinary(String name, int type, byte[] data) {
        this._put(5, name);
        int totalLen = data.length;
        if(type == 2) {
            totalLen += 4;
        }

        this._buf.writeInt(totalLen);
        this._buf.write(type);
        if(type == 2) {
            this._buf.writeInt(totalLen - 4);
        }

        int before = this._buf.getPosition();
        this._buf.write(data);
        int after = this._buf.getPosition();
    }

    protected void putUUID(String name, UUID val) {
        this._put(5, name);
        this._buf.writeInt(16);
        this._buf.write(3);
        this._buf.writeLong(val.getMostSignificantBits());
        this._buf.writeLong(val.getLeastSignificantBits());
    }

    protected void putSymbol(String name, Symbol symbol) {
        this._putString(name, symbol.getSymbol(), 14);
    }

    protected void putString(String name, String value) {
        this._putString(name, value, 2);
    }

    private void _putString(String name, String s, int type) {
        this._put(type, name);
        this._putValueString(s);
    }

    protected void putObjectId(String name, ObjectId oid) {
        this._put(7, name);
        this._buf.writeIntBE(oid._time());
        this._buf.writeIntBE(oid._machine());
        this._buf.writeIntBE(oid._inc());
    }

    private void putPattern(String name, Pattern p) {
        this._put(11, name);
        this._buf.writeCString(p.pattern());
        this._buf.writeCString(BSON.regexFlags(p.flags()));
    }

    private void putMinKey(String name) {
        this._put(-1, name);
    }

    private void putMaxKey(String name) {
        this._put(127, name);
    }

    /** @deprecated */
    @Deprecated
    protected void _put(int type, String name) {
        this._buf.write(type);
        this._buf.writeCString(name);
    }

    /** @deprecated */
    @Deprecated
    protected void _putValueString(String s) {
        this._buf.writeString(s);
    }

    void _reset(Buffer b) {
        b.position(0);
        b.limit(b.capacity());
    }

    /** @deprecated */
    @Deprecated
    protected int _put(String str) {
        return this._buf.writeCString(str);
    }

    /** @deprecated */
    @Deprecated
    public void writeInt(int x) {
        this._buf.writeInt(x);
    }

    /** @deprecated */
    @Deprecated
    public void writeLong(long x) {
        this._buf.writeLong(x);
    }

    /** @deprecated */
    @Deprecated
    public void writeCString(String s) {
        this._buf.writeCString(s);
    }
}
