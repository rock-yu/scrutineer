package au.com.oakware.openscrutineer;

import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class StringIdAndVersion extends AbstractIdAndVersion {

	public static final IdAndVersionFactory FACTORY = StringIdAndVersionFactory.INSTANCE;
	private static final int HASH_MULTIPLIER = 31;

	private final String id;

	public StringIdAndVersion(String id, long version) {
		super(version);
		this.id = id;
	}

	public String getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof StringIdAndVersion)) {
			return false;
		}
		StringIdAndVersion other = (StringIdAndVersion) obj;
		return this.id.equals(other.id) && this.getVersion() == other.getVersion();
	}

	@Override
	public int hashCode() {
		return id.hashCode() * HASH_MULTIPLIER + Long.hashCode(getVersion());
	}

	@Override
	public int compareTo(IdAndVersion other) {
		if (other instanceof StringIdAndVersion) {
			StringIdAndVersion o = (StringIdAndVersion) other;
			int cmp = this.id.compareTo(o.id);
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
		return appender.append(id, ((StringIdAndVersion) other).id);
	}

	@Override
	protected void writeId(ObjectOutputStream objectOutputStream) throws IOException {
		objectOutputStream.writeUTF(getId());
	}

}
