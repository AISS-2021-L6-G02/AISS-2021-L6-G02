package aiss.model;

public class Genre {
		private String id;
		private String name;
		private String description;
	
		/*Constructores*/
		public Genre(String string) {
			this.name = string;
			this.description = "no description";
		}

		public Genre() {
			super();
		}

		public Genre(String id, String name, String description) {
			super();
			this.id = id;
			this.name = name;
			this.description = description;
		}

		public Genre(String name, String description) {
			super();
			this.name = name;
			this.description = description;
			
		}

		/*Getters y Setters*/
		public String getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public String getDescription() {
			return description;
		}

		public void setId(String id) {
			this.id = id;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((description == null) ? 0 : description.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Genre other = (Genre) obj;
			if (description == null) {
				if (other.description != null)
					return false;
			} else if (!description.equals(other.description))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Genre [id=" + id + ", name=" + name + ", description=" + description + "]";
		}
		
		
		
		
		
		
		


}
