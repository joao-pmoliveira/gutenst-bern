export default async function AuthorPage({ params }: { params: Promise<{ id: string }> }) {

    const { id } = await params;

    const res = await fetch(`${process.env.API_URL}/api/authors/${id}`);

    if (!res.ok) throw new Error("Failed to load author");

    const data = await res.json();

    const author = ({
        id: data["id"],
        name: data["name"]
    })

    return <h1>Page of {author.name || "N/A"}</h1>
}
