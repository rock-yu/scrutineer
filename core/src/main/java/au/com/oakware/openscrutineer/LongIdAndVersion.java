package au.com.oakware.openscrutineer;

import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class LongIdAndVersion extends AbstractIdAndVersion {

	public static final IdAndVersionFactory FACTORY = LongIdAndVersionFactory.INSTANCE;
	private static final int HASH_MULTIPLIER = 31;

    private final long id;
    private String idString;

    public LongIdAndVersion(long id, long version) {
    	super(version);
        this.id = id;
    }

    public String getId() {
        if (idString == null) {
            idString = Long.toString(id);
        }
        return idString;
    }

    public long getLongId() {
        return id;
    }

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof LongIdAndVersion)) {
			return false;
		}
		LongIdAndVersion other = (LongIdAndVersion) obj;
		return this.id == other.id && this.getVersion() == other.getVersion();
	}

	@Override
	public int hashCode() {
		return Long.hashCode(id) * HASH_MULTIPLIER + Long.hashCode(getVersion());
	}

	@Override
	public int compareTo(IdAndVersion other) {
		if (other instanceof LongIdAndVersion) {
			LongIdAndVersion o = (LongIdAndVersion) other;
			int cmp = Long.compare(this.id, o.id);
			return cmp != 0 ? cmp : Long.compare(this.getVersion(), o.getVersion());
		}
		return super.compareTo(other);
	}

	@Override
	protected HashCodeBuilder appendId(HashCodeBuilder appender) {
		return appender.append(id);
	}

	@Override
	protected CompareToBuilder appendId(CompareToBuilder appender, IdAndVersion other) {
		return appender.append(id, ((LongIdAndVersion)other).id);
	}

	@Override
	protected void writeId(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeLong(id);
	}

}
