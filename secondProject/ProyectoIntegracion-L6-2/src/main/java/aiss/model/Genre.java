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
		
		
		
		
		
		


}
