import { Book } from "@/types/Book";

export default function BookCardProgress(props: { book: Book }) {
    const book = props.book;
    const progress = 40;

    return (
        <div className="grid grid-cols-[1fr_3fr] gap-2 pb-1">
            <div className="bg-blue-200 w-full">
                <img alt={`Cover of ${book.title}`} src={book.cover}
                    className="rounded-sm shadow-md"
                />
            </div>
            <div className="flex flex-col">
                <div className="grow">
                    <h2 className="text-md line-clamp-2 font-bold grow">
                        {book.title}
                    </h2>
                    <p className="text-sm line-clamp-1 text-gray-900 font-light italic">
                        {book.authors.map(author => author.name).join("; ")}
                    </p>
                </div>
                <p className="text-sm text-bottle-green italic">{progress}%</p>
                <button className="rounded-sm text-sm text-white bg-bottle-green w-2/3 min-w-40">Update Progress</button>
            </div>
        </div>
    )
}
