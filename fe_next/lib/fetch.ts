import { BookApi } from "@/types/Api";
import { Book } from "@/types/Book";
import { mapBookApi } from "./BookMapping";

export default async function getBooks(): Promise<Book[]> {
    const res = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/api/catalog`, {
        next: { revalidate: 0 }
    });

    if (!res.ok) throw new Error("Failed to fetch books");

    const data: BookApi[] = await res.json();
    return data.slice(0, 6).map(book => mapBookApi(book));
}
